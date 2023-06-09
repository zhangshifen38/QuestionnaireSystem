let qid = ''
let questionnare = {}
let answerList = []

onload = () => {
    qid = $util.getPageParam('qid')
    console.log(qid)
    fetchQuestionnaire(qid)

}

const handleShowButton = () => {
    $('#charts').html('')
    questionnare.questionList.forEach((question,qidx) => {
        let typeName = ['','单选题','多选题','填空题','矩阵题','量表题']
        if(question.questionType === 3){
            return
        }
        $('#charts').append(`
            <div class="data-info">
                <div class="question-title">${qidx}.${typeName[question.questionType]}: ${question.questionName}</div>
                <div class="chart" id="chart${qidx}"></div>
            </div>
            
        `)
    })
    questionnare.questionList.forEach((question,qidx)=>{
        if(question.questionType === 3){
            return
        }
        let chart = echarts.init(document.getElementById(`chart${qidx}`));
        let barOption = {}
        if(question.questionType!==4) {
            barOption = {
                xAxis: {
                    type: 'category',
                    data: question.option.map((optionItem) => {
                        return optionItem.optionDescription
                    })
                },
                yAxis: {},
                series: [
                    {
                        type: 'bar',
                        data: question.option.map((optionItem) => {
                            let num = 0
                            answerList.forEach((answer) => {
                                if (answer.questionId === question.id) {
                                    answer.answerList.forEach((answerContent) => {
                                        if (answerContent.optionId === optionItem.id) {
                                            num = num + 1;
                                        }
                                    })
                                }
                            })
                            return num
                        })
                    }
                ]
            }
        }else{
            let lf = question.leftTitle.split(',')
            barOption = {
                xAxis: {
                    type: 'category',
                    data: lf
                },
                yAxis: {},
                series: question.option.map((optionItem) => {
                    return {
                        type:'bar',
                        data:lf.map((lTitle) => {
                            let num = 0
                            answerList.forEach((answer) => {
                                if (answer.questionId === question.id) {
                                    answer.answerList.forEach((answerContent) => {
                                        if (answerContent.optionId === optionItem.id && answerContent.optionContent === lTitle) {
                                            num = num + 1;
                                        }
                                    })
                                }
                            })
                            return num
                        })
                    }
                })
            }
            console.log(barOption)
        }
        chart.setOption(barOption)
    })
}

const fetchQuestionnaire = (id) => {
    let params={
        id:id
    }
    $.ajax({
        url: API_BASE_URL + '/questionnaire/selectFullQuestionnaireById',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            questionnare = res.data
            $('#qName').text(questionnare.questionnaireName)
            $('#qContent').text(questionnare.questionnaireContent)
            fetchAnswerList(id);
            //console.log(res.data)
        }
    })
}

const fetchAnswerList = (id) => {
    let params={
        id:id
    }
    $.ajax({
        url: API_BASE_URL + '/answer/selectFullAnswerList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            answerList = res.data
        }
    })
}