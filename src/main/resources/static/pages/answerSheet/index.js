let mode
let questionnaire={}
let answerList = [];
onload = () => {
  let qid=getQueryVariable("id")
  if(qid!==false){
    mode=0
  }else {
    mode = $util.getPageParam('mode')
    qid = $util.getPageParam('qid')
  }
  console.log(mode,qid)
  if(mode === 1){
    $('#tmode').text('问卷预览')
    $('#submitMode').text('返回')
  }
  let params={
    id:qid
  }
  $.ajax({
    url: API_BASE_URL + '/questionnaire/selectFullQuestionnaireById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      questionnaire=res.data
      if(mode===0 && res.data.status!=="2"){
        $('#qName').text('问卷未公开')
        $('#qContent').text('')
      }else {
        $('#qName').text(questionnaire.questionnaireName)
        $('#qContent').text(questionnaire.questionnaireContent)
        questionnaire.questionList.forEach((question, index) => {
          if (question.questionType === 1) {
            handleAppendSingleChoice(question, index)
          } else if (question.questionType === 2) {
            handleAppendMultiChoice(question, index)
          } else if (question.questionType === 3) {
            handleAppendFillBlank(question, index)
          } else if (question.questionType === 4) {
            handleAppendMatrix(question, index)
          } else if (question.questionType === 5) {
            handleAppendGauge(question, index)
          }
        })
      }
    }
  })
}

const handleSubmitButton=()=>{
  if(mode === 1){
    window.opener=null
    window.close()
    return
  }
  console.log(questionnaire)
  answerList=[]
  missList=[]
  questionnaire.questionList.forEach((question,qidx)=>{
    if(question.questionType === 1){
      let val = $(`input[name="SingleChoice${qidx}"]:checked`).val()
      if(question.requireFlag && val === undefined){
        missList.push(qidx)
      }else{
        answerList[qidx] = {
          questionId:question.id,
          answerList:[]
        }
        answerList[qidx].answerList[0]={
          optionId:val
        }
      }
    }else if(question.questionType === 2){
      let mlist=[]
      $(`input[name=MultiChoice${qidx}]`).each(function (){
        if($(this).is(":checked")){
          mlist[mlist.length] = {
            optionId: $(this).val()
          }
        }
      })
      if(question.requireFlag && mlist.length === 0){
        missList.push(qidx)
      }else {
        answerList[qidx] = {
          questionId: question.id,
          answerList: mlist
        }
      }
    }else if(question.questionType === 3){
      let content = $(`textarea[name=FillBlank${qidx}]`).val()
      if(question.requireFlag && (content === undefined || content === '')){
        missList.push(qidx)
      }else{
        answerList[qidx] = {
          questionId:question.id,
          answerList:[]
        }
        answerList[qidx].answerList[0] = {
          optionContent:content
        }
      }
    }else if(question.questionType === 4){
      let lf = question.leftTitle.split(',')
      let mlist = []
      let ok = true
      lf.forEach((lTitle,lidx)=>{
        let val = $(`input[name="MatrixRadio${qidx}_${lidx}"]:checked`).val()
        if(question.requireFlag && val === undefined){
          ok=false
        }else{
          mlist[mlist.length] = {
            optionId:val,
            optionContent:lTitle
          }
        }
      })
      if(!ok){
        missList.push(qidx)
      }else {
        answerList[qidx] = {
          questionId: question.id,
          answerList: mlist
        }
      }
    }else if(question.questionType === 5){
      let val = $(`input[name="GaugeScore${qidx}"]:checked`).val()
      if(question.requireFlag && val === undefined){
        missList.push(qidx)
      }else{
        answerList[qidx] = {
          questionId:question.id,
          answerList:[]
        }
        answerList[qidx].answerList[0]={
          optionId:val
        }
      }
    }
  })
  if(missList.length !== 0){
    alert(`题目${missList}是必答题，请作答！`)
  }else{
    let params=answerList
    $.ajax({
      url: API_BASE_URL + '/answer/insertFullAnswerList',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        alert(res.message)
        if(res.code === "200"){
          window.opener=null
          window.close()
        }
      }
    })
  }
  console.log(answerList)
}
const handleAppendGauge = (question,index)=>{
  $('#problem').append(`
    <div class="question" id="question${index}" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle${index}">${index}.量表题：${question.questionName}</span>
        <span class="must-answer" id="mustAnswer${index}">${question.requireFlag?"必答题":"选做题"}</span>
      </div>
      <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
      </div>
    </div>
  `)
  $(`#question${index} .bottom`).html('')
  $(`#question${index} .bottom`).append(`
    <div>${question.option[0].optionDescription}</div>
  `)
  question.option.map(item => {
    $(`#question${index} .bottom`).append(`
      <div>
        <label class="radio-inline">
          <input type="radio" name="GaugeScore${index}" value="${item.id}" />${item.optionScore}
        </label>
      </div>
    `)
  })
  $(`#question${index} .bottom`).append(`
    <div>${question.option[question.option.length - 1].optionDescription}</div>
  `)
}
const handleAppendMatrix = (question,index) => {
  let lf = question.leftTitle.split(',');
  $('#problem').append(`
    <div class="question" id="question${index}" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle${index}">${index}.矩阵题：${question.questionName}</span>
        <span class="must-answer" id="mustAnswer${index}">${question.requireFlag?"必答题":"选做题"}</span>
      </div>
      <div class="bottom">
        <table class="table">
          <thead class="thead" id="table${index}Head">
            <tr>
              <th></th>
            </tr>
          </thead>
          <tbody class="tbody" id="table${index}Body">
          </tbody>
        </table>
      </div>
    </div>
  `)
  lf.map((item,idx)=>{
    $(`#question${index} .bottom tbody`).append(`
      <tr class="tr${idx}">
        <td>${item}</td>
      </tr>
    `)
    question.option.map((option) => {
      $(`#question${index} .bottom tbody .tr${idx}`).append(`
        <td>
          <input type="radio" name="MatrixRadio${index}_${idx}" value="${option.id}">
        </td>
      `)
    })
  })
  question.option.map(item => {
    $(`#question${index} .bottom thead tr`).append(`
      <th>${item.optionDescription}</th>
    `)
  })
}
const handleAppendFillBlank = (question,index) => {
  $('#problem').append(`
    <div class="question" id="question${index}" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle${index}">${index}.填空题：${question.questionName}</span>
        <span class="must-answer" id="mustAnswer${index}">${question.requireFlag?"必答题":"选做题"}</span>
      </div>
      <div class="bottom">
        
    </div>
  `)
  $(`#question${index} .bottom`).html(`
    <textarea class="form-control" name="FillBlank${index}" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
  `)
}
const handleAppendMultiChoice = (question,index) => {
  $('#problem').append(`
    <div class="question" id="question${index}" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle${index}">${index}.多选题：${question.questionName}</span>
        <span class="must-answer" id="mustAnswer${index}">${question.requireFlag?"必答题":"选做题"}</span>
      </div>
      <div class="bottom">
      </div>
    </div>
  `)

  $(`#question${index} .bottom`).html('')
  question.option.map(item => {
    $(`#question${index} .bottom`).append(`
      <div style="display: flex; align-items: center;">
        <label class="checkbox-inline">
          <input type="checkbox" name="MultiChoice${index}" value="${item.id}">${item.optionDescription ? item.optionDescription : ''}
        </label>
      </div>
    `)
  })

}
const handleAppendSingleChoice = (question,index) => {
  $('#problem').append(`
    <div class="question" id="question${index}" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle${index}">${index}.单选题：${question.questionName}</span>
        <span class="must-answer" id="mustAnswer${index}">${question.requireFlag?"必答题":"选做题"}</span>
      </div>
      <div class="bottom">
      </div>
    </div>
  `)

  $(`#question${index} .bottom`).html('')
  question.option.map(item => {
    $(`#question${index} .bottom`).append(`
      <div style="display: flex; align-items: center; margin-bottom: 3px;">
        <label class="radio-inline">
          <input type="radio" name="SingleChoice${index}" value="${item.id}">${item.optionDescription ? item.optionDescription : ''}
        </label>
      </div>
    `)
  })
}
function getQueryVariable(variable)
{
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
    if(pair[0] == variable){return pair[1];}
  }
  return(false);
}