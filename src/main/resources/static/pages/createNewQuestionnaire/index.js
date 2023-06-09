let projectId=''
let type=''

onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  projectId=$util.getPageParam('projectId')
  let typeId=$util.getPageParam('type')
  if(typeId === "1"){
    type="学生"
  }else if(typeId === "2"){
    type="老师"
  }
  console.log(projectId,type)
}

const handleCreate = () => {
  let params={
    projectId:projectId,
    questionnaireName:$('#surveyName').val(),
    questionnaireContent:$('#surveyDescription').val(),
    questionnaireType:type,
    status:"1",
    startTime:$('#startDate').val() && new Date($('#startDate').val()).getTime(),
    stopTime:$('#endDate').val() && new Date($('#endDate').val()).getTime(),
    createdBy:$util.getItem('userInfo').username,
    creationDate:new Date().getTime()
  }
  if (!params.questionnaireName) return alert('调查名称不能为空！')
  if (!params.questionnaireContent) return alert('调查说明不能为空！')
  if (!params.startTime) return alert('开始时间不能为空！')
  if (!params.stopTime) return alert('结束时间不能为空！')
  console.log(params)
  $.ajax({
    url: API_BASE_URL + '/questionnaire/addQuestionnaire',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      alert(res.message)
      if(res.code === '200'){
        $util.setPageParam('questionnaireId',res.data)
        location.href='/pages/designQuestionnaire/index.html'
      }
    }
  })
  //open('/pages/designQuestionnaire/index.html')
}