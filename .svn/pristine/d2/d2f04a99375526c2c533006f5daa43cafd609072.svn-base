// by jjt

// 스크립트 테스트용
function testAlert(){
    alert("This is test!");
}

//var serverURL = "http://192.168.2.74:8031/merp/getJsonData.api"; // jjt
var serverURL = "http://192.168.2.199:8080/merp/getJsonData.api"; // hjh
// api 콜
function server_Call(eventClick) {
var Json_Data_Return;
    $.ajax({
        method: 'POST',
        url: serverURL,
        dataType: 'json',
        data: eventClick,
        timeout: 1000,
        async: false,
        success: function(res, xhr) {
            console.log(res.CODE);
            console.log(res.TEXT);
            console.log(typeof(res));
            Json_Data_Return = res;
        },
        error: function(res, flagError, xhr) {
            console.log(flagError);
            console.log(res.CODE);
            console.log(res.TEXT);
            phonon.alert(res.TEXT, '경고');
        }
    });
    return Json_Data_Return;
}

// 숫자 콤마처리
function number_format(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// api 데이터 컨트롤
function data_controll(data){
    console.log("server_Call method -> " +data.CODE);
    console.log(data.TEXT);
    // 성공여부 판별
    if(data.CODE == '001'){

    }
}

function insa_out(insa_gubun_num, data){
    var insa_data = data;
    var insa_call = server_Call(insa_data);
    console.log(insa_call.CODE);
    console.log(insa_call.TEXT);
    if(insa_call.CODE == '001'){
        $("#insa_out" + insa_gubun_num).children().remove();
        $("#insa_out" + insa_gubun_num).append(insa_call.HTML);
    }
}

function insa_outMore(insa_gubun_num, data){
	var insa_data = data;
	var insa_call = server_Call(insa_data);
	console.log(insa_call.TEXT);
	if(insa_call.CODE == '001'){
		$("#moreBtn").remove();
		$("#insa_out" + insa_gubun_num).append(insa_call.HTML);
	}
}

function geayak_out(geayak_gubun_num, data){
    var geayak_data = data;
    var geayak_call = server_Call(geayak_data);
    console.log(geayak_call.TEXT);
    if(geayak_call.CODE == '001'){
        $("#geayak_out" + geayak_gubun_num).children().remove();
        $("#geayak_out" + geayak_gubun_num).append(geayak_call.HTML);
    }
}