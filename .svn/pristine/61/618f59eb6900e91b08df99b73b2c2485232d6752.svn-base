phonon.options({
    navigator: {
        defaultPage: 'home',
        animatePages: true,
        templateRootDirectory: './html',
        enableBrowserBackButton: true,
        useHash: true
    },
    i18n: null
});

var app = phonon.navigator();
var platform = phonon.device.os;
document.addEventListener('deviceready' ,function() {
    console.log('현재 아이폰 종류 = ' + device.model);
    var model = device.model;
    localStorage.setItem('iModel', model);
});
//var serverURL = "http://192.168.0.49:8031/merp/getJsonData.api"; // 정종태주임쪽 URL
//var serverURL = "http://192.168.2.199:8087/merp/getJsonData.api"; // 홍준호주임쪽 URL
//var serverURL = "http://192.168.2.22:8031/merp/getJsonData.api"; // 실운영서버 URL
//var serverURL = "http://app3.genexon.co.kr/merp/getJsonData.api"; // 실운영서버 URL
var serverURL = "https://app3.genexon.co.kr/merp/getJsonData.api"; // 실운영서버 URL 시큐리티 버전
//var server2 = 'http://192.168.0.49:8031';
//var server2 = 'http://192.168.2.199:8087';
//var server2 = 'http://app3.genexon.co.kr';
var server2 = 'https://app3.genexon.co.kr'; // 시큐리티 버전
sessionStorage.setItem('s_url', server2);
console.log(sessionStorage.getItem('s_url'));
var scr_height = localStorage.getItem('device_height');
// http://ddirty.tistory.com/50
// .p12 인증서 푸시 가능 파일로 변환 시 터미널 명령어 나와있는 참고페이지
// 맥 변경 시 인증서 내보내기 후 변경할 맥북으로 옮긴 후 개발자 홈페이지에서 프로젝트 인증서 발행 후 빌드
// 기간 만료(대부분 1년) 시 다시 인증서 재발급 후 적용 뒤 빌드
var com_cd;
var user_id;
var user_pw;
var regid;
var regid_test = document.getElementById('regid').value;
console.log(regid_test);
var emp_cd;
var cd_vl_ky;
var v_m_board;
var v_m_grade;
var v_m_todo;
var m_default;
var mapDiv;
var data_authority_role_cd;
var menu_authority_role_cd;

var testYN = false; // test여부확인 true = test용 / false - 상용

var device_type = '';
if (platform == "Android") {
    device_type = 'A'; // 동작되는 플랫폼이 안드로이드이면 A로 device_type에 값 지정
} else if(platform = "iOS") {
    device_type= 'I'; // 동작되는 플랫폼이 iOS면 I로 device_type에 값 지정
}

window.addEventListener('load', function() {
    FastClick.attach(document.body);
    console.log('FastClick Active');
}, false);

function serverCall(eventClick) {  // 서버와의 통신을 위한 ajax 구문
var Json_Data_Return;
    $.ajax({
        method: 'POST',
        url: serverURL,
        dataType: 'json',
        data: eventClick,
        timeout: 1000,
        async: false,
        success: function(res, xhr) {
            Json_Data_Return = res;
        },
        error: function(res, flagError, xhr) {
            console.log(flagError);
            console.log(res.CODE);
            console.log(res.TEXT);
            setTimeout(function() {
                app.changePage('warn_page', null);
            }, 500);
        }
    });
    console.log("ajax 밖..." + JSON.stringify(Json_Data_Return));
    return Json_Data_Return;
}

app.on({page: 'home', preventClose: false, content: 'home.html'}, function(activity) {

    setTimeout(function() {  // 자동로그인 로직
        console.log('자동로그인 확인');
        document.addEventListener("deviceready", function() {
            if (platform == "iOS") { // 플랫폼에 따른 device_id 값 적용 (다른 페이지도 동일함)
                regid = localStorage.getItem('token');
            } else {
                regid = localStorage.getItem('regid');
            }
            console.log(regid);
            console.log(device_type);

            // api호출
            var autolg_data;
            if(testYN == false) {
                autolg_data = {gubun_num: '1', reg_id: regid, device_type: device_type};
            } else {
                autolg_data = {gubun_num: '1', reg_id: regid_test, device_type: device_type};
            }
            console.log('호출 전');
            var autolg_call = serverCall(autolg_data);
            console.log('호출 후');
//            console.log(autolg_call.CODE);
//            console.log(autolg_call.TEXT);
//            console.log(autolg_call.DATA.MB_COMPANY_CD);
//            console.log(autolg_call.DATA.MB_EMP_CD);
//            console.log(autolg_call.PNS_CNT);
            if (autolg_call.CODE == '014') { // 코드 014는 자동로그인을 나타냄
                /* console.log(autolg_call.CODE);
                console.log(autolg_call.TEXT);
                console.log(autolg_call.DATA.DATA_AUTHORITY_ROLE_CD);
                console.log(autolg_call.DATA.MENU_AUTHORITY_ROLE_CD);
                console.log(autolg_call.DATA.MB_EMP_NM);
                console.log(autolg_call.DATA.MB_SCD);
                console.log(autolg_call.DATA.MB_SCDNM); */
                var logo_img = autolg_call.LOGO; // 스플래시 화면에서 각 GA들의 로고와 문구를 서버에서 받아와 적용할 수 있도록 데이터를 받음.
                localStorage.setItem('logo_img', logo_img);
                console.log(localStorage.getItem('logo_img'));
                var g_mian = autolg_call.MAIN;
                localStorage.setItem('main', g_mian);
                console.log(localStorage.getItem('main'));
                var g_info = autolg_call.INFO;
                localStorage.setItem('info', g_info);
                console.log(localStorage.getItem('info'));
                var g_apns = autolg_call.APNS;
                localStorage.setItem('apns', g_apns);
                console.log(localStorage.getItem('apns'));
                var pnsCnt = autolg_call.PNS_CNT; //
                localStorage.setItem('pnsCnt', pnsCnt);
                console.log(localStorage.getItem('pnsCnt'));
                var fileName = autolg_call.FILENAME;
                localStorage.setItem('fileName' ,fileName);
                console.log('사원이미지 파일네임 = ' + localStorage.getItem('fileName'));
//                if (fileName == "") {
//                    localStorage.setItem('fileName', '1x_user.png');
//                    localStorage.setItem('fileURL', 'file:///Users/junho/Library/Developer/CoreSimulator/Devices/C3562BF3-5146-4661-B157-568B6CBDF52B/data/Containers/Bundle/Application/A89C4CC5-7F8D-4FB1-BCC3-B05CCF427AFC/SmartGA.app/www/images/');
//                } else {
                    localStorage.setItem('fileName', fileName);
                    var fileURL = autolg_call.URL;
                    localStorage.setItem('fileURL', fileURL.URL);
                    console.log('ERP URL = ' + JSON.stringify(fileURL.URL));
//                }
                var data_role = autolg_call.DATA.DATA_AUTHORITY_ROLE_CD;
                localStorage.setItem('data_role', data_role);
                console.log(localStorage.getItem('data_role'));
                var menu_role = autolg_call.DATA.MENU_AUTHORITY_ROLE_CD;
                localStorage.setItem('menu_role', menu_role);
                console.log(localStorage.getItem('menu_role'));
                var comp_cd = autolg_call.DATA.MB_COMPANY_CD;
                localStorage.setItem('comp_cd', comp_cd);
                console.log(localStorage.getItem('comp_cd'));
                var emp_cd = autolg_call.DATA.MB_EMP_CD;
                sessionStorage.setItem('emp_cd', emp_cd);
                console.log(sessionStorage.getItem('emp_cd'));
                var gender = autolg_call.DATA.GENDER;
                localStorage.setItem('gender', gender);
                console.log(localStorage.getItem('gender'));
                var emp_nm = autolg_call.DATA.MB_EMP_NM;
                sessionStorage.setItem('emp_nm', emp_nm);
                console.log(sessionStorage.getItem('emp_nm'));
                app.changePage('splash_screen', null);
            } else {
//                phonon.alert(autolg_call.TEXT, '',  false, '확인');
            }
        });
    }, 2000);

    activity.onCreate(function() {
                      
        var chkBox = document.getElementById('login_info');
        if (localStorage.getItem('checkValue') == 'true') {
            console.log('회사코드, 사원코드 저장됨');
            $("input:checkbox[id='login_info']").prop("checked", true);
                document.getElementById('mb_company_cd').value = localStorage.getItem('company');
                document.getElementById('mb_user_id').value = localStorage.getItem('identify');
                
            } else {
                $("input:checkbox[id='login_info']").prop("checked", false);
            }
            document.querySelector('.list').on('click', memLogin, false);
            function memLogin() {
                if ($('input:checkbox[id="login_info"]').is(":checked") == true) {
                    var checkValue = $('input:checkbox[id="login_info"]').is(":checked");
                    console.log(checkValue);
                    localStorage.setItem('checkValue', checkValue);
                    console.log('저장된 체크상태 = ' + localStorage.getItem('checkValue'));
                } else {
                    var checkValue = $('input:checkbox[id="login_info"]').is(":checked");
                    localStorage.setItem('checkValue', checkValue);
                    console.log('변경된 체크상태 = ' + checkValue);
                }
            }
                      
        document.getElementById('info_string').innerHTML = "GA기업고객을 위한 모바일 서비스 채널입니다.<br>ID/PW는 ERP와 동일하며, 로그인 관련 문의는<br>본사로 연락 바랍니다. 감사합니다.";
        document.querySelector('#btn_login').on('click', function() {
            com_cd = document.getElementById('mb_company_cd').value;
            localStorage.setItem('company', com_cd);
            user_id = document.getElementById('mb_user_id').value;
            localStorage.setItem('identify', user_id);
            user_pw = document.getElementById('mb_emp_pw').value;
            if (platform == "iOS") {
                regid = localStorage.getItem('token');
            } else {
                regid = localStorage.getItem('regid');
            }
            com_cd = com_cd.toUpperCase();
            console.log(com_cd);
            console.log(user_id);
            console.log(user_pw);
//            console.log(regid);
            console.log('현재 모델 = ' + localStorage.getItem('iModel'));
            if (com_cd.length === 0 && user_id.length === 0 && user_pw.length === 0) {
            phonon.alert('회사코드, 사원코드, 비밀번호를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length === 0 && user_id.length !== 0 && user_pw.length === 0) {
            phonon.alert('회사코드, 비밀번호를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length !== 0 && user_id.length === 0 && user_pw.length === 0) {
            phonon.alert('사원코드, 비밀번호를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length === 0 && user_id.length === 0 && user_pw.length !== 0) {
            phonon.alert('회사코드, 사원코드를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length === 0 && user_id.length !== 0 && user_pw.length !== 0) {
            phonon.alert('회사코드를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length !== 0 && user_id.length === 0 && user_pw.length !== 0) {
            phonon.alert('사원코드를 입력해 주세요', '', false, '확인');
            } else if (com_cd.length !== 0 && user_id.length !== 0 && user_pw.length === 0) {
            phonon.alert('비밀번호를 입력해 주세요', '', false, '확인'); // 작성 데이터의 부재 시 보여주는 안내문구(약간의 exception의 기능 수행)
            } else {
                // API response 요청
                var id_DATA = user_id;
                localStorage.setItem('u_id', id_DATA);
                var login_data;
                if (testYN == false) {
                    console.log(regid);
                    login_data = {gubun_num: '2', server_company_cd: com_cd, reg_id: regid, mb_user_id: user_id, inputpassword: user_pw, device_type: device_type};
                } else {
                    login_data = {gubun_num: '2', server_company_cd: com_cd, reg_id: regid_test, mb_user_id: user_id, inputpassword: user_pw, device_type: device_type};
                }
                var login_call = serverCall(login_data); // api 호출
                if (login_call.CODE == '001') {
                    /*console.log(document.domain);
                    console.log(login_call.DATA.MB_EMP_CD);
                    console.log(login_call.DATA.DATA_AUTHORITY_ROLE_CD);
                    console.log(login_call.DATA.MENU_AUTHORITY_ROLE_CD);
                    console.log(login_call.DATA.MB_EMP_NM);
                    console.log(login_call.DATA.MB_SCD);
                    console.log(login_call.DATA.MB_SCDNM);*/
                    var logo_img = login_call.LOGO;
                    localStorage.setItem('logo_img', logo_img);
                    console.log(localStorage.getItem('logo_img'));
                    console.log(login_call.PNS_CNT);
                    var g_main = login_call.MAIN;
                    localStorage.setItem('main', g_main);
                    console.log(localStorage.getItem('main'));
                    var g_info = login_call.INFO;
                    localStorage.setItem('info', g_info);
                    console.log(localStorage.getItem('info'));
                    var g_apns = login_call.APNS;
                    localStorage.setItem('apns', g_apns);
                    console.log(localStorage.getItem('apns'));
                    var loginPns = login_call.PNS_CNT;
                    localStorage.setItem('pnsCnt', loginPns);
                    console.log(localStorage.getItem('pnsCnt'));
                    var fileName = login_call.FILENAME;
//                    if (fileName == "") {
//                        localStorage.setItem('fileName', '1x_user.png');
//                        localStorage.setItem('fileURL', 'file:///Users/junho/Library/Developer/CoreSimulator/Devices/C3562BF3-5146-4661-B157-568B6CBDF52B/data/Containers/Bundle/Application/A89C4CC5-7F8D-4FB1-BCC3-B05CCF427AFC/SmartGA.app/www/images/');
//                    } else {
                        localStorage.setItem('fileName', fileName);
                        var fileURL = login_call.URL;
                        localStorage.setItem('fileURL', fileURL.URL);
                        console.log('ERP URL = ' + JSON.stringify(fileURL.URL));
//                    }
                    com_cd = login_call.DATA.MB_COMPANY_CD;
                    localStorage.setItem('com_cd', com_cd);
                    console.log(localStorage.getItem('com_cd'));
                    var emp_cd = login_call.DATA.MB_EMP_CD;
                    sessionStorage.setItem('emp_cd', emp_cd); // 세션 스토리지에 사원코드 저장
                    var emp_nm = login_call.DATA.MB_EMP_NM;
                    sessionStorage.setItem('emp_nm', emp_nm); // 세션 스토리지에 사원명 저장
                    var gender = login_call.DATA.GENDER;
                    localStorage.setItem('gender', gender);  // 로컬 스토리지에 성별 구분 저장
                    console.log(localStorage.getItem('gender'));
                    var data_role = login_call.DATA.DATA_AUTHORITY_ROLE_CD;
                    localStorage.setItem('data_role', data_role); // 로컬 스토리지에 데이터 권한 저장
                    var menu_role = login_call.DATA.MENU_AUTHORITY_ROLE_CD;
                    localStorage.setItem('menu_role', menu_role); // 로컬 스토리지에 메뉴 권한 저장
                    console.log(sessionStorage.getItem('emp_cd'));
                    console.log(localStorage.getItem('data_role'));
                    console.log(localStorage.getItem('menu_role'));
                    app.changePage('splash_screen', null); // 로고(GA사)이미지 화면으로 이동
                } else {
                    // 비밀번호가 틀릴 시 현재 비밀번호를 없애고 새로운 비밀번호를 넣기위해 비밀번호란 제거
                    phonon.alert(login_call.TEXT, '', false, '확인');
                    document.getElementById('mb_emp_pw').value='';
                }
                // 로그인 성공 시 다음 화면으로 넘어감/실패 시 실패 카운트를 계산하여 alert의 내용 다르게 표현되게 설정
                // 화면넘김 시 splash 이미지 생성 가능 유무를 위해 임의 이벤트 작성함.
            }
        });
    });
});

app.on({page: 'splash_screen', preventClose: false, content: 'splash_screen.html'}, function(activity) {
   
    activity.onCreate(function() {
                      
        var ratio = window.devicePixelRatio;
        var qratio = ratio*2;
        console.log(scr_height);
        console.log(ratio);
        document.getElementById('com_bground').style.backgroundImage = 'url("' + server2 + '/img/logo/intro_' + localStorage.getItem('logo_img') + '.jpg")';
//        document.getElementById('com_title').src = server2 + "/img/logo/title_" + localStorage.getItem('logo_img') + ".jpg";
//        document.getElementById('com_image').src = server2 + "/img/logo/logo_" + localStorage.getItem('logo_img') + ".jpg";
//        document.getElementById('logo_image').style.marginTop = scr_height/qratio-50 + "px";
//        console.log(document.getElementById('logo_image').style.marginTop);
        console.log('스플레시 화면 도착');
        board_menu();
        setTimeout(function() {
            console.log('1초 뒤 메인화면 이동');
            if (localStorage.getItem('com_cd') !== null || localStorage.getItem('comp_cd') !== null) {
                console.log('여기 도착했니1?');
                   app.changePage('list_page', 'main');
//                location.href = "#!list_page/main";
            } else {
                console.log('여기 도착했니2?');
                app.changePage('list_page', 'main');
            }
        }, 1000);
        // 스플레시 화면은 1초간 유지 후 메인화면으로 이동
    });
});

app.on({page: 'findpw', preventClose: true, content: 'findPassword.html'}, function(activity) {

    activity.onCreate(function() {
        var fpwTitle = document.getElementById('fpw_title');
        center_title(fpwTitle);
        document.querySelector('#confirm_pw').on('click', function() {
            var com_cd = document.getElementById('mb_company_cd1').value;
            var user_id = document.getElementById('mb_user_id1').value;
            var c_num = document.getElementById('cellphone').value;
            if (platform == "iOS") {
                regid = localStorage.getItem('token');
            } else {
                regid = localStorage.getItem('regid');
            }
            console.log(regid);
            if (com_cd.length === 0 && user_id.length === 0 && c_num.length === 0) {
                phonon.alert('회사코드, 아이디(사원번호), 핸드폰 번호를 입력해주세요','', false, '확인');
            } else if(com_cd.length !== 0 && user_id.length === 0 && c_num.length === 0) {
                phonon.alert('아이디(사원번호), 핸드폰 번호를 입력해주세요', '', false, '확인');
            } else if(com_cd.length === 0 && user_id.length !== 0 && c_num.length === 0) {
                phonon.alert('회사코드, 핸드폰 번호를 입력해주세요', '', false, '확인');
            } else if(com_cd.length === 0 && user_id.length === 0 && c_num.length !== 0) {
                phonon.alert('회사코드, 아이디(사원번호)를 입력해주세요', '', false, '확인');
            } else if(com_cd.length !== 0 && user_id.length !== 0 && c_num.length === 0) {
                phonon.alert('핸드폰 번호를 입력해주세요', '', false, '확인');
            } else if(com_cd.length === 0 && user_id.length !== 0 && c_num.length !== 0) {
                phonon.alert('회사코드를 입력해주세요', '', false, '확인');
            } else if(com_cd.length !== 0 && user_id.length === 0 && c_num.length !== 0) {
                phonon.alert('아이디(사원번호)를 입력해주세요', '', false, '확인');
            } else {
                // api호출
                 
                var fpw_data;
                if (testYN == false) {
                    fpw_data = {gubun_num: '3', server_company_cd: com_cd, reg_id: regid, mb_user_id: user_id, cellphone: c_num, device_type: device_type};
                } else {
                    fpw_data = {gubun_num: '3', server_company_cd: com_cd, reg_id: regid_test, mb_user_id: user_id, cellphone: c_num, device_type: device_type};
                }
                var fpw_call = serverCall(fpw_data);
                if (fpw_call.CODE == '001') {
                    var rtLogin = phonon.alert('문자서비스로 임시비밀번호가 발급되었습니다. 로그인 후 변경 바랍니다.', '', false, '확인');
                    rtLogin.on('confirm', function() {
                          app.changePage('home', null);
                    });
                } else {
                    phonon.alert(fpw_call.TEXT, '', false, '확인');
                }
                // response된 json값에서 success이면 아래 alert이 나옴
                // fail 값이 넘어오면 다른 alert 값을 보여줌(fail의 경우 - 사용중단된 사원번호로 요청 시)
            }
        });
    });

    activity.onClose(function(self) {
        self.close();
        history.go(0);
    });
});

app.on({page: 'setting', preventClose: false, content: 'setting.html'}, function(activity) {

    activity.onCreate(function() {
        var width = window.innerWidth;
        console.log('화면너비 = ' + width + ', ' + typeof(width));
        var settingTitle = document.getElementById('setHead');
        center_title(settingTitle);
        if (platform == "iOS") {
            regid = localStorage.getItem('token');
        } else {
            regid = localStorage.getItem('regid');
        }
        emp_cd = sessionStorage.getItem('emp_cd');
        if(localStorage.getItem('com_cd') !== null) {
            com_cd = localStorage.getItem('com_cd');
        } else if (localStorage.getItem('comp_cd') !== null) {
            com_cd = localStorage.getItem('comp_cd');
        }
        data_authority_role_cd = localStorage.getItem('data_role');
        menu_authority_role_cd = localStorage.getItem('menu_role');
        var img_noti_st = document.getElementById('img_noti_st');
        var img_alog_st = document.getElementById('img_alog_st');
        var cnt = 1;
        var k_flag = '';
        var k_flag1 = '';
        var alog_flag = 0;
        var nt_flag = 0;
        var group_code1 = 'NOTIFICATION';
        var group_code2 = 'AUTO_LOGIN';
        // api 호출
        var setting_data;
        if (testYN == false) {
            setting_data = {gubun_num: '7', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid, cd_vl_ky: 'SETTING', menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
        } else {
            setting_data = {gubun_num: '7', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid_test, cd_vl_ky: 'SETTING', menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
        }
        var setting_call = serverCall(setting_data);
        if (setting_call.CODE == '001') {
            $.each(setting_call.DATA.APP_ENVIRONMENT_SETTING_LIST, function (index, item) {
                if (item.SETTING_NAME == 'NOTIFICATION') {
                    k_flag = item.SETTING_VALUE;
                    console.log(k_flag);
                   console.log(document.getElementById('btn_noti_bell').style.backgroundImage);
                   if (k_flag == '01' || document.getElementById('btn_noti_bell').style.backgroundImage == 'url("img/on.png")') {
                        img_noti_st.src = "images/off.png";
                        k_flag = '02';
                    } else {
                        img_noti_st.src = "images/on.png";
                        k_flag = '01';
                    }
                } else if (item.SETTING_NAME == 'AUTO_LOGIN') {
                    k_flag1 = item.SETTING_VALUE;
                    console.log(k_flag1);
                    if (k_flag1 == '01') {
                        img_alog_st.src = "images/off.png";
                        k_flag1 = '02';
                    } else {
                        img_alog_st.src = "images/on.png";
                        k_flag1 = '01';
                    }
                }
            });
        }

        document.querySelector('#l_my_info').on('click', function() {
            if (cnt%2 == 1){
                img_m_info.src="img/bg_list_sel.png";
                go_myPage();
            } else {
                img_m_info.src="img/bg_list.png";
            }
            img_m_info.src="img/bg_list.png";
            cnt++;
        });

        document.querySelector('#l_chg_pw').on('click', function() {
            if (cnt%2 == 1){
                img_fd_pw.src="img/bg_list_sel.png";
                setTimeout(function() {
                    app.changePage('rewritepw', null);
                }, 100);
            } else {
                img_fd_pw.src="img/bg_list.png";
            }
            img_fd_pw.src="img/bg_list.png";
            cnt++;
        });
        document.querySelector('#l_main_st').on('click', function() {
            if (cnt%2 == 1){
                img_m_set.src="img/bg_list_sel.png";
                setTimeout(function() {
                    app.changePage('mainset', null);
                }, 100);
            } else {
                img_m_set.src="img/bg_list.png";
                console.log(cnt);
            }
            img_m_set.src="img/bg_list.png";
            cnt++;
        });
        document.querySelector('#l_noti_st').on('click', function() {
            console.log(k_flag);
            if (k_flag == '02'){
                img_noti_st.src="images/on.png";
                nt_flag = k_flag;
                k_flag = '01';
            } else {
                img_noti_st.src="images/off.png";
                nt_flag = k_flag;
                k_flag = '02';
            }

            // api 호출
            var setting_data1;
            if (testYN == false) {
                setting_data1 = {gubun_num: '8', server_company_cd: com_cd, mb_emp_cd: emp_cd, group_code: group_code1, setting_value: nt_flag, reg_id: regid, menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
            } else {
                setting_data1 = {gubun_num: '8', server_company_cd: com_cd, mb_emp_cd: emp_cd, group_code: group_code1, setting_value: nt_flag, reg_id: regid_test, menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
            }
            var setting_call1 = serverCall(setting_data1);
        });
        document.querySelector('#l_alog_st').on('click', function() {
            console.log(k_flag1);
            if (k_flag1 == '01'){
                img_alog_st.src="images/off.png";
                alog_flag = k_flag1;
                k_flag1 = '02';
            } else {
                img_alog_st.src="images/on.png";
                alog_flag = k_flag1;
                k_flag1 = '01';
            }
            // api 호출
            var setting_data2;
            if (testYN == false) {
                setting_data2 = {gubun_num: '8', server_company_cd: com_cd, mb_emp_cd: emp_cd, group_code: group_code2, setting_value: alog_flag, reg_id: regid, menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
            } else {
                setting_data2 = {gubun_num: '8', server_company_cd: com_cd, mb_emp_cd: emp_cd, group_code: group_code2, setting_value: alog_flag, reg_id: regid_test, menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
            }
            var setting_call2 = serverCall(setting_data2);
        });
    });
});
function go_myPage() {
    console.log(window.location);
    if (sessionStorage.getItem('emp_cd') == "ADMIN" || sessionStorage.getItem('emp_cd') == "SADMIN") {
        console.log(sessionStorage.getItem('emp_cd'));
        var mPageCase = phonon.alert('관리자 사번의 마이페이지는 존재하지 않습니다.', '', false, '확인');
        mPageCase.on('confirm', function() { });
    } else {
        search_title();
        view_page(localStorage.getItem('info'));
        if (window.location.hash != '#!list_page/mypage') {
            window.location.href = "#!list_page/mypage";
        } else {
            app.changePage('list_page', 'mypage');
        }
    }
}

app.on({page: 'rewritepw', preventClose: false, content: 'rewritepw.html'}, function (activity) {

    activity.onCreate(function () {
        var width = window.innerWidth;
        console.log('화면너비 = ' + width + ', ' + typeof(width));
        var rwpTitle = document.getElementById('rpwHead');
        center_title(rwpTitle);
                      
        user_id = localStorage.getItem('u_id');
        emp_cd = sessionStorage.getItem('emp_cd');

        document.querySelector('#btn_accept').on('click', function() {
            var prePw = document.getElementById('pre_pw');
            var chgPw = document.getElementById('chg_pw');
            var r_chgPw = document.getElementById('r_chg_pw');
            if (platform == "iOS") {
                regid = localStorage.getItem('token');
            } else {
                regid = localStorage.getItem('regid');
            }
            emp_cd = sessionStorage.getItem('emp_cd');
            console.log(prePw.value);
            console.log(chgPw.value);
            console.log(r_chgPw.value);
            console.log(emp_cd);
            if(prePw.value.length === 0) {
                phonon.alert('현재 비밀번호를 입력해 주시기 바랍니다.', '', false, '확인');
            } else if (chgPw.length === 0) {
                phonon.alert('변경 될 비밀번호를 입력해 주시기 바랍니다.', '',  false, '확인');
            } else if (r_chgPw.length === 0) {
                phonon.alert('변경 될 비밀번호를 재입력해 주시기 바랍니다.', '',  false, '확인');
            } else if (chgPw != r_chgPw) {
                var wrongPw = phonon.alert('변경 될 비밀번호가 맞지 않습니다. 다시 입력해 주세요', '',  false, '확인');
                wrongPw.on('confirm', function() {
                    prePw.value = '';
                    chgPw.value = '';
                    r_chgPw.value = '';
                });
            } else {
                var prePasswd = prePw.value;
                var chgPasswd = chgPw.value;
                // api 호출
                var cpw_data;
                if (testYN == false) {
                    cpw_data = {gubun_num: '4', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid, mb_user_id: user_id, inputpassword: prePasswd, device_type: device_type};
                } else {
                    cpw_data = {gubun_num: '4', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid_test, mb_user_id: user_id, inputpassword: prePasswd, device_type: device_type};
}
                var cpw_call = serverCall(cpw_data);
                if (cpw_call.CODE === '001') {
                    var rpw_data;
                    if (testYN == false) {
                    rpw_data = {gubun_num: '5', server_company_cd: com_cd, reg_id: regid, mb_user_id: user_id, inputpassword: chgPw, device_type: device_type};
                    } else {
                    rpw_data = {gubun_num: '5', server_company_cd: com_cd, reg_id: regid_test, mb_user_id: user_id, inputpassword: chgPw, device_type: device_type};
                    }
                    var rpw_call = serverCall(rpw_data);
                }
                if (cpw_call.CODE == '001' && rpw_call.CODE == '001') {
                    var rwLogin = phonon.alert('비밀번호가 변경되었습니다.', '', false, '확인');
                    rwLogin.on('confirm', function() {
                        window.setTimeout(function() {
                            localStorage.removeItem('data_role');
                            localStorage.removeItem('menu_role');
                            window.sessionStorage.clear();
                            if (sessionStorage.getItem('emp_cd') === null && localStorage.getItem('data_role') == null) {
                                app.changePage('home', null);
                            }
                        }, 500);
                    });
                } else {
                    console.log(cpw_call.CODE);
                    console.log(cpw_call.TEXT);
                    if (cpw_call.CODE !== '001') {
                        phonon.alert(cpw_call.TEXT, '', false, '확인');
                    } else if (rpw_call.CODE !== '001') {
                        console.log(rpw_call.CODE);
                        console.log(rpw_call.TEXT);
                        phonon.alert(rpw_call.TEXT, '', false, '확인');
                    }
                }
            }
        });
    });
});

app.on({ page: 'mainset', preventClose: false, content: 'mainset.html'}, function(activity) {
    activity.onCreate(function() {

        var width = window.innerWidth;
        console.log('화면너비 = ' + width + ', ' + typeof(width));
        var msetTitle = document.getElementById('msetHead');
        center_title(msetTitle);
        var m_board = document.getElementById('m_board');
		var m_grade_list = document.getElementById('m_grade_list');
        var m_grade = document.getElementById('m_grade');
        var m_todo = document.getElementById('m_todo');
        var group_code = 'MAINSCREEN_SETTING';
        if (platform == "iOS") {
            regid = localStorage.getItem('token');
        } else {
            regid = localStorage.getItem('regid');
        }
        emp_cd = window.sessionStorage.getItem('emp_cd');
        if (localStorage.getItem('com_cd') !== null) {
            com_cd = localStorage.getItem('com_cd');
        } else if (localStorage.getItem('comp_cd') !== null) {
            com_cd = localStorage.getItem('comp_cd');
        }
        data_authority_role_cd = window.localStorage.getItem('data_role');
        menu_authority_role_cd = window.localStorage.getItem('menu_role');

        v_m_board = m_board.value;
        v_m_grade = m_grade.value;
        v_m_todo = m_todo.value;

        console.log(device_type);
        console.log(group_code);
        console.log(emp_cd);
        var mainset_data;
        if (testYN == false) {
            mainset_data = {gubun_num: '7', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid, device_type: device_type, group_code: group_code, cd_vl_ky: 'MAIN', menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
        } else {
            mainset_data = {gubun_num: '7', server_company_cd: com_cd, mb_emp_cd: emp_cd, reg_id: regid_test, device_type: device_type, group_code: group_code, cd_vl_ky: 'MAIN', menu_authority_role_cd: menu_authority_role_cd, data_authority_role_cd: data_authority_role_cd};
        }
        var mainset_call = serverCall(mainset_data);
        if (mainset_call.CODE == '001') {
            console.log(mainset_call.CODE);
            console.log(mainset_call.TEXT);
            console.log(mainset_call.DATA.APP_ENVIRONMENT_SETTING_LIST);
            console.log(menu_authority_role_cd);
            if (data_authority_role_cd != '99') {
                m_grade_list.style.display = "none";
            }
            $.each(mainset_call.DATA.APP_ENVIRONMENT_SETTING_LIST, function (index, item) {
                console.log(item.SETTING_VALUE);
                console.log(item.SETTING_NAME);
                if (item.SETTING_VALUE === '01') {
                    m_board.checked = true;
                    m_grade.checked = false;
                    m_todo.checked = false;
                } else if (item.SETTING_VALUE === '02') {
                    m_board.checked = false;
                    m_grade.checked = true;
                    m_todo.checked = false;
                } else if (item.SETTING_VALUE === '03') {
                    m_board.checked = false;
                    m_grade.checked = false;
                    m_todo.checked = true;
                }
            });
        } else {
            phonon.alert(mainset_call.TEXT, '', false, '확인');
        }

        console.log(v_m_board);
        console.log(v_m_grade);
        console.log(v_m_todo);

        console.log(m_board.checked);
        console.log(m_grade.checked);
        console.log(m_todo.checked);
        document.querySelector('#m_board_list').on('click', function() {
            console.log(111);
            if (m_board.checked == false) {
                m_board.checked = true;
                call_Main(group_code, v_m_board, device_type);
            } else {
                call_Main(group_code, v_m_board, device_type);
            }
        });

        document.querySelector('#m_grade_list').on('click', function() {
            if (m_grade.checked == false) {
                m_grade.checked = true;
                call_Main(group_code, v_m_grade, device_type);
            } else {
                call_Main(group_code, v_m_grade, device_type);
            }
        });

        document.querySelector('#m_todo_list').on('click', function() {
            if (m_todo.checked == false) {
                m_todo.checked = true;
                call_Main(group_code, v_m_todo, device_type);
            } else {
                call_Main(group_code, v_m_todo, device_type);
            }
        });
    });

    function call_Main(gcode, main, type) {
        if (localStorage.getItem('com_cd') !== null) {
            var mSet_com_cd = localStorage.getItem('com_cd');
        } else if (localStorage.getItem('comp_cd') !== null) {
            var mSet_com_cd = localStorage.getItem('comp_cd');
        }
        if (platform == "iOS") {
            regid = localStorage.getItem('token');
        } else {
            regid = localStorage.getItem('regid');
        }
        emp_cd = sessionStorage.getItem('emp_cd');
        data_authority_role_cd = localStorage.getItem('data_role');
        menu_authority_role_cd = localStorage.getItem('menu_role');
        var mSetData;
       if (testYN == false) {
            mSetData = {gubun_num: '8', server_company_cd: mSet_com_cd, mb_emp_cd: emp_cd, reg_id: regid, data_authority_role_cd: data_authority_role_cd, menu_authority_role_cd: menu_authority_role_cd, group_code: gcode, setting_value: main, device_type: type};
       } else {
            mSetData = {gubun_num: '8', server_company_cd: mSet_com_cd, mb_emp_cd: emp_cd, reg_id: regid_test, data_authority_role_cd: data_authority_role_cd, menu_authority_role_cd: menu_authority_role_cd, group_code: gcode, setting_value: main, device_type: type};
       }
        var mSetCall = serverCall(mSetData);
    }
});

app.on({page: 'list_page', preventClose: false, content: 'list_page.html'}, function(activity) {

    activity.onHashChanged(function(gubun_string) {
        console.log(gubun_string);
        console.log('1');
        var nowHash = "#!list_page/" + gubun_string + "/";
        console.log(nowHash);
        if (localStorage.getItem('nowHash') != nowHash) {
            localStorage.setItem('nowHash', nowHash);
            console.log(localStorage.getItem('nowHash'));
        }
        if(gubun_string == 'main') { // 메인페이지
            console.log('여기는 언제 들어오니???');
            view_page(localStorage.getItem('main'));
            search_pnsNum();
            search_title();
        }
    });

    activity.onCreate(function() {
        console.log('2');
        console.log('노티 이동경로' + localStorage.getItem('notimove'));
        if (localStorage.getItem('notimove') != null) {
            app.changePage(localStorage.getItem('notimove'), null);
            localStorage.removeItem('notimove');
        } else { }
        console.log('height = ' + document.getElementById('page_frame').height);
        var pageTitle = document.getElementById('hWidth');
        reload_page(location.hash, pageTitle);
        var iModel = window.device.model;
        if (iModel == "iPhone10,3" || iModel == "iPhone10,6" || iModel == "x86_64") {
        console.log("아이폰X다!!!");
//                      $(".side-menu1").css("background-color", "#419ef7");
//                      $(".header-bar").css("top", "44px");
//                      $(".accordion-nav").css("padding-top", "47px");
                      $(".footer").css("background-color", "#419ef7");
                      $(".footer").css("padding-bottom", "30px");
        } else {

        }
    });
    function reload_page(page, title) {
       console.log('3');
       console.log(location.href);
       center_title(title);
       search_pnsNum();
    }
});

function center_title(strTitle) {
    console.log('4');
    var width = window.innerWidth;
    console.log('화면너비 = ' + width + ', ' + typeof(width));
    strTitle.style.webkitTransform = "translateX(" + width/2 + "px) translateX(-50%)";
    console.log(strTitle.style.webkitTransform);
}

function search_title(gubun) {
    console.log('5');
    var test = "";
    $("#page_frame").contents().find('title').text("");
    var count = 1;
    var refreshIntervalId = setInterval(fname, 700);
    function fname() {
        console.log(count + " 번째");
        count++;
        test = $("#page_frame").contents().find('title').text();
        console.log("test = > " + test);
        if (test.length > 0 || count == 10) {
            console.log('종료');
            clearInterval(refreshIntervalId);
            console.log('타이틀을 알려줘!!! -> ' + test);
            $("#hWidth").text(test);
        }
    }
}

function view_page(gubun, bulbd_no) {
    console.log('6');
    var page_gubun = gubun;
    var page_emp_cd = sessionStorage.getItem('emp_cd');
    var page_search_emp_cd = sessionStorage.getItem('emp_cd');
    var page_bulbd_no = bulbd_no;
    if (localStorage.getItem('com_cd') !== null) {
        var page_com_cd = localStorage.getItem('com_cd');
    } else if (localStorage.getItem('comp_cd') !== null) {
        var page_com_cd = localStorage.getItem('comp_cd');
    }
    if (platform == "iOS") {
        page_regid = localStorage.getItem('token');
    } else {
        page_regid = localStorage.getItem('regid');
    }
    var page_menu_role = localStorage.getItem('menu_role');
    var page_data_role = localStorage.getItem('data_role');
    var page_device_type = device_type;
    var page_lat = localStorage.getItem('lat');
    var page_lng = localStorage.getItem('lng');
    document.getElementById('page_gubun').value = page_gubun;
    document.getElementById('page_emp_cd').value = page_emp_cd;
    document.getElementById('page_com_cd').value = page_com_cd;
    if (testYN == false) {
        document.getElementById('page_regid').value = page_regid;
    } else {
        document.getElementById('page_regid').value = '32f501e739b007b308bc2702b2e8b01581d0409b3ba6069d7dba332891a5605c';
    }
    document.getElementById('page_menu_role').value = page_menu_role;
    document.getElementById('page_data_role').value = page_data_role;
    document.getElementById('page_device_type').value = page_device_type;
    document.getElementById('page_search_emp_cd').value = page_search_emp_cd;
    document.getElementById('page_lat').value = page_lat;
    document.getElementById('page_lng').value = page_lng;
    document.getElementById('page_view_gubun').value = page_view_gubun;
    document.getElementById('page_kind').value = page_bulbd_no;
    var page_formAgent = document.getElementById('page_formAgent');
    page_formAgent.target = "page_frame";
    page_formAgent.action = serverURL;
    page_formAgent.submit();
}

function search_pnsNum() {
    console.log('7');
    var pns_gubunNum = localStorage.getItem('apns');
    if (localStorage.getItem('com_cd') !== null) {
        var pns_com_cd = localStorage.getItem('com_cd');
    } else if (localStorage.getItem('comp_cd') !== null) {
        var pns_com_cd = localStorage.getItem('comp_cd');
    }
    if (platform == "iOS") {
        var pns_regid = localStorage.getItem('token');
        console.log(pns_regid);
    } else {
        var pns_regid = localStorage.getItem('regid');
    }
    var pns_emp_cd = sessionStorage.getItem('emp_cd');
    var pns_menu_role = localStorage.getItem('menu_role');
    var pns_data_role = localStorage.getItem('data_role');
    var pns_device_type = device_type;
    var pnsNumData;
    if (testYN == false) {
        pnsNumData = {gubun_num: pns_gubunNum, server_company_cd: pns_com_cd, regid: pns_regid, mb_emp_cd: pns_emp_cd, data_authority_role_cd: pns_data_role, menu_authority_role_cd: pns_menu_role, device_type: pns_device_type};
    } else {
        pnsNumData = {gubun_num: pns_gubunNum, server_company_cd: pns_com_cd, regid: regid_test, mb_emp_cd: pns_emp_cd, data_authority_role_cd: pns_data_role, menu_authority_role_cd: pns_menu_role, device_type: pns_device_type};
    }
    console.log(pnsNumData);
    var pnsNumCall = serverCall(pnsNumData);
    console.log('pns 결과코드 = ' + pnsNumCall.CODE);
    console.log('pns 결과 값 = ' + pnsNumCall.TEXT);
    console.log(pnsNumCall.PNS_CNT);
    var nPnsNum = pnsNumCall.PNS_CNT;
    if (localStorage.getItem('pnsCnt') !== null) {
        localStorage.removeItem('pnsCnt');
        console.log(localStorage.getItem('pnsCnt'));
        localStorage.setItem('pnsCnt', nPnsNum);
        setPnsBell();
    }
}

function setPnsBell() {
    var nPns = localStorage.getItem('pnsCnt');
    console.log('------------------------------------------------------------------');
    console.log('PNS_CNT = ' + nPns);
    console.log('------------------------------------------------------------------');
    var inPns = parseInt(nPns);
    console.log('------------------------------------------------------------------');
    console.log('int(PNS_CNT) = ' + inPns);
    console.log('------------------------------------------------------------------');
    if (inPns > 0 && inPns < 9) {
        if (inPns == 1) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_1.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 2) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_2.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 3) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_3.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 4) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_4.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 5) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_5.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 6) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_6.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns == 7) {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_7.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else {
            document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_8.png")';
            document.getElementById('nConNum').innerHTML = inPns.toString();
        }
    } else if (inPns >= 9) {
        console.log('9이상인 경우')
        document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline_9.png")';
        if (inPns >= 9 && inPns < 99) {
            document.getElementById('nConNum').innerHTML = inPns.toString();
        } else if (inPns >= 99) {
            document.getElementById('nConNum').innerHTML = '99+';
        }
    } else {
        document.getElementById('btn_noti_bell').style.backgroundImage = 'url("images/bell-outline.png")';
        document.getElementById('nConNum').style.display = "none";
    }
    // 앱 내부의 뱃지를 표현할 수 있도록 PNS 갯수에 따라 이미지 및 텍스트를 조정할 수 있는 함수
}

//function appandURL(url) {
//    console.log(url);
//    if (url != null) {
//        localStorage.setItem('urlName', url);
//        console.log(localStorage.getItem('urlName'));
//    }
//}

function getPositionPoint(gubun_num) {
    navigator.geolocation.getCurrentPosition(onSuccess, onError, {enableHighAccuracy: true, maximumAge: 5000, timeout: 5000});
    
    function onSuccess(position) {
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;
        localStorage.setItem('lat', lat);
        localStorage.setItem('lng', lng);
        console.log('위도 = ' + localStorage.getItem('lat'));
        console.log('경도 = ' + localStorage.getItem('lng'));
        console.log(lat);
        console.log(lng);
        view_page(gubun_num);
    }
    
    function onError (error) {
		if (platform.toUpperCase() == "ANDROID") {
//			callGPS();
		} else if (platform.toUpperCase() == "IOS") {
			var offGPS = phonon.alert('GPS 기능이 꺼져있습니다. 설정>개인 정보 보호>위치 서비스 메뉴에서 기능을 켜주시기 바랍니다.', '', false, '확인');
			offGPS.on('confirm', function() {
				app.previousPage;
			});
		}
        console.log('code : ' + error.code + '\n' + 'message : ' + error.message);
    }
}

app.on({page: 'warn_page', preventClose: false, content: 'warn_page.html'}, function(activity) {
    activity.onCreate(function() {
        document.getElementById('reCall_Serv').on('click', function() {
            console.log('테스트 = ' + history.go(-1));
            var pre_page = app.previousPage;
            console.log(pre_page);
            var warn_alert = phonon.alert('이전 페이지로 돌아갑니다.', '', false, '확인');
            warn_alert.on('confirm', function() {
                console.log(localStorage.getItem('nowHash'));
                if (localStorage.getItem('nowHash') != null) {
					console.log('현재 페이지 = ' + localStorage.getItem('nowHash'));
                    location.assign(localStorage.getItem('nowHash'));
                    history.go(0);
                } else if (pre_page == null) {
					app.changePage('home', null);
//                    app.previousPage;
                }
            });
        });
    });
       // 로그인, 각종 페이지를 서버에 요청 시 response가 없을 때 보여주는 페이지
});

app.on({page: 'noti_list', preventClose: false, content: 'noti_list.html'}, function(activity) {
       // iframe으로 변경 예정
    activity.onCreate(function() {
        console.log(document.getElementById('noti_connect').style.display);
        var width = window.innerWidth;
        console.log('화면너비 = ' + width + ', ' + typeof(width));
        var notiTitle = document.getElementById('notiHead');
        center_title(notiTitle);
        var lpns_gubunNum = 'APNS01';
        if (localStorage.getItem('com_cd') !== null) {
            var lpns_com_cd = localStorage.getItem('com_cd');
            lpns_com_cd = lpns_com_cd.toUpperCase();
        } else if (localStorage.getItem('comp_cd') !== null) {
            var lpns_com_cd = localStorage.getItem('comp_cd');
            lpns_com_cd = lpns_com_cd.toUpperCase();
        }
        if (platform == "iOS") {
            lpns_regid = localStorage.getItem('token');
        } else {
            lpns_regid = localStorage.getItem('regid');
        }
        var lpns_emp_cd = sessionStorage.getItem('emp_cd');
        var lpns_menu_role = localStorage.getItem('menu_role');
        var lpns_data_role = localStorage.getItem('data_role');
        var lpns_device_type = device_type;
        console.log(lpns_gubunNum + ', ' + lpns_com_cd + ', ' + lpns_regid + ', ' + lpns_emp_cd + ', ' + lpns_menu_role + ', ' + lpns_data_role + ', ' + lpns_device_type);
        var lpnsNumData = {gubun_num: lpns_gubunNum, server_company_cd: lpns_com_cd, reg_id: lpns_regid, mb_emp_cd: lpns_emp_cd, data_authority_role_cd: lpns_data_role, menu_authority_role_cd: lpns_menu_role, device_type: lpns_device_type};
        var lpnsNumCall = serverCall(lpnsNumData);
        if(lpnsNumCall.CODE == '001') {
            $('#noti-page').children().remove();
            $.each(lpnsNumCall.DATA.RESULT, function (index, item) {
                console.log('index의 타입 = ' + typeof(index) + ', ' + index);
                if (index != 'undefined' || index != 'NaN' || index != null) {
                    if (index >= 0 && index < index+1) {
                        console.log('알림 아이템 = ' + item.BULBD_NO);
                        var html = '';
                        html += '<div class="view_list" onclick="go_notiDetail(' + item.BULBD_NO + ', ' + item.BULT_NO + ');">';
                        html += '<div class="board">';
                        html += '<header>' + item.TITL + ' | ' + item.BULT_NM + '</header>';
                        html += '<h4>작성자 : ' + item.INPP_NM + ' | 게시판 번호 : ' + item.BULT_NO + '</h4>';
                        html += '<span class="pull-right more"><a href="#"><img src="img/bg_list.png" style="width:50%;"></a></span>';
                        html += '</div>';
                        html += '</div>';
                        $('#noti-page').append(html);
                    } else {

                    }
                } else if (lpnsNumCall.DATA.RESULT == null) {
                    phonon.alert('모든 알림을 확인했습니다.', '', false, '확인');
                }
            });
        } else {
            app.changePage('warn_page', null);
        }
    });
       // 확인하지 않은 PNS알림 리스트를 보여주는 페이지
});

function go_notiDetail(bulbd_no, bult_no) {
    document.getElementById('list_content').style.display = "none";
    document.getElementById('noti_connect').style.display = "block";
    console.log('알림 내용을 보여라!!!');
    var noti_gubunNum = 'BOARD01';
    if (localStorage.getItem('com_cd') !== null) {
        var noti_com_cd = localStorage.getItem('com_cd');
    } else if (localStorage.getItem('comp_cd') !== null) {
        var noti_com_cd = localStorage.getItem('comp_cd');
    }
    if (platform == "iOS") {
        noti_regid = localStorage.getItem('token');
    } else {
        noti_regid = localStorage.getItem('regid');
    }
    var noti_emp_cd = sessionStorage.getItem('emp_cd');
    var noti_menu_role = localStorage.getItem('menu_role');
    var noti_data_role = localStorage.getItem('data_role');
    var noti_device_type = device_type;
    
    console.log(noti_gubunNum);
    console.log(noti_com_cd);
    console.log(noti_regid);
    console.log(noti_emp_cd);
    console.log(noti_menu_role);
    console.log(noti_data_role);
    console.log(noti_device_type);
    console.log(ifm_height)
    console.log(bulbd_no);
    console.log(bult_no);
    
    document.getElementById('noti_gubunNum').value = noti_gubunNum;
    document.getElementById('noti_com_cd').value = noti_com_cd;
    document.getElementById('noti_regid').value = noti_regid;
    document.getElementById('noti_emp_cd').value = noti_emp_cd;
    document.getElementById('noti_menu_role').value = noti_menu_role;
    document.getElementById('noti_data_role').value = noti_data_role;
    document.getElementById('noti_device_type').value = noti_device_type;
    document.getElementById('noti_bulbd_no').value = bulbd_no;
    document.getElementById('noti_bult_no').value = bult_no;
    
    var noti_formAgent = document.getElementById('noti_formAgent');
    noti_formAgent.target = "noti_page_frame";
    noti_formAgent.action = serverURL;
    console.log(noti_formAgent.target);
    console.log(noti_formAgent.action);
    noti_formAgent.submit();
}

function openUrl(url, readerMode) {
    SafariViewController.isAvailable(function (available) {
        if (available) {
        SafariViewController.show({
                url: url,
                hidden: false,
                animated: false,
                transition: 'curl',
                enterReaderModeIfAvailable: readerMode,
                tintColor: "#00ffff",
                barColor: "#0000ff",
                controlTintColor: "#ffffff"
            },
            function (result) {
                if (result.event == 'opened') {
                    console.log('opened');
                } else if (result.event == 'loaded') {
                    console.log('loaded');
                } else if (result.event == 'closed') {
                    console.log('closed');
                }
            },
            function(msg) {
                console.log("KO: " + msg);
            })
        } else {
            window.open(url, '_blank', 'location=yes');
        }
    })
}

function dismissSafari() {
    SafariViewController.hide();
}

app.start();
