onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = $util.getPageParam('seeProject')
  console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
  fetchquestionnaireList(projectId)
}

const fetchProjectInfo = (id) => {
  let params = {
    id
  }
  $.ajax({
    url: API_BASE_URL + '/project/selectProjectById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#projectDescription').text(info.projectContent)
    }
  })
}

const fetchquestionnaireList = (projectId) => {
  let params = {
    projectId:projectId
  }
  $.ajax({
    url: API_BASE_URL + '/questionnaire/selectQuestionnaireByProjectId',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      questionnaireList = Array.from(res.data)
      $('#qTable').html('')
      if(questionnaireList.length > 0) {
        questionnaireList.forEach((item,index) => {
          $('#qTable').append(`
            <tr>
               <td>${index+1}</td>
               <td>${item.questionnaireName}</td>
               <td>${item.startTime}</td>
               <td>
                  <button type="button" class="btn btn-link" onclick="handlePublication('${item.id}')">发布</button>
                  <button type="button" class="btn btn-link" onclick="handleClose('${item.id}')">关闭</button>
                  <button type="button" class="btn btn-link" onclick="handleGetLink('${item.id}')">链接</button>
                  <button type="button" class="btn btn-link" onclick="handleStatistic('${item.id}')">统计</button>
              </td>
            </tr>
          `)
        })
      }
    }
  })
}

const handleStatistic = (id) => {
  $util.setPageParam('qid',id);
  location.href = '/pages/statistic/index.html'
}

const handleClose = (id)=>{
  let params={
    id:id,
    status:3
  }
  $.ajax({
    url: API_BASE_URL + '/questionnaire/updateQuestionnaire',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if(res.code === 500){
        alert('关闭失败')
      }else{
        alert('关闭成功')
      }
    }
  })
}
const handleGetLink=(id)=>{
  let params={
    id:id
  }
  $.ajax({
    url: API_BASE_URL + '/questionnaire/selectQuestionnaireById',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res.data)
      if(res.code === 500){
        alert(res.message)
      }else if(res.data.status!=="2"){
        alert('问卷未公开，无法访问！')
      }else{
        open('/pages/answerSheet/index.html?id='+id)
      }
    }
  })
}
const handlePublication = (id) =>{
  let params={
    id:id,
    status:2
  }
  $.ajax({
    url: API_BASE_URL + '/questionnaire/updateQuestionnaire',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if(res.code === 500){
        alert(res.message)
      }else{
        copyToClip(API_BASE_URL+'/pages/answerSheet/index.html?id='+id)
        alert(res.message+'\n链接已经复制到剪贴板，或者点击”链接“即可跳转')
      }
    }
  })
}

function copyToClip(content) {
  var aux = document.createElement("input");
  aux.setAttribute("value", content);
  document.body.appendChild(aux);
  aux.select();
  document.execCommand("copy");
  document.body.removeChild(aux);
}
