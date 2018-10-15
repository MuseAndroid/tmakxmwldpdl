$(function(){

	document.addEventListener( 'deviceready', app_init, false );

	var isClick = false;

	function app_init(){
		document.addEventListener( 'backbutton', 	app_backbutton, false );
		document.addEventListener( 'pause', 		app_pause, false );
		document.addEventListener( 'resume', 		app_resume, false );
	}

	function app_backbutton(){
        if (isClick == false) {
            console.log(window.location.href);
            console.log(window.location.hash);
            console.log(window.location.pathname);
            console.log(phonon.navigator().currentPage);
            console.log(phonon.navigator().previousPage);
            var curr = phonon.navigator().currentPage;
            var prev = phonon.navigator().previousPage;
            var title = $("#page_frame").contents().find('title').text();
            console.log(title);
            title = title.trim(0, 7);
            console.log(title);
            var ifmtitle = $("#page_frame").contents().find('title').text();
            if( curr == 'home'){
                setToast();
                isClick = true;
                setTimeout(function(){ isClick = false; }, 3000);
            } else if (title == "Smart GA" && !(curr == "setting" || curr == "noti_list")) {
                console.log('들어온 곳1');
                setToast();
            isClick = true;
            setTimeout(function(){ isClick = false; }, 3000);
            } else if ((curr == 'setting' || curr == 'noti_list') && prev == 'list_page') {  // 설정에서 뒤로가기
                console.log("내가 여기에0");
                var test = "";
                $("#page_frame").contents().find('title').text("");
                var count = 1;
                var refreshIntervalId = setInterval(fname, 700);
                function fname(){
                    console.log(count + " 번째");
                    count++;
                    test = $("#page_frame").contents().find('title').text();
                    console.log("test = > " + test);
                    if(test.length > 0 || count == 5){
                        console.log('종료');
                        clearInterval(refreshIntervalId);
                        console.log('타이틀이 무엇이냐!!!! -> ' + test);
                        $("#hWidth").text(test);
                    }
                }
                console.log(localStorage.getItem('nowHash'));
                location.href = localStorage.getItem('nowHash');
            } else {
                history.go(-1);
                console.log(phonon.navigator().currentPage);
                if (curr == 'list_page') {
                    var nowtitle = $("#hWidth").text();
                    var test = "";
                    $("#page_frame").contents().find('title').text("");
                    var count = 1;
                    var refreshIntervalId = setInterval(fname, 700);
                    function fname(){
                        console.log(count + " 번째");
                        count++;
                        test = $("#page_frame").contents().find('title').text();
                        console.log("test = > " + test);
                        if(test.length > 0 || count == 5){
                            console.log('종료');
                            clearInterval(refreshIntervalId);
                            console.log('타이틀이 무엇이냐!!!! -> ' + test);
                            $("#hWidth").text(test);
                        }
                    }
                }
            }
		} else {
		    sessionStorage.clear();
            localStorage.removeItem('data_role');
            localStorage.removeItem('menu_role');
            localStorage.removeItem('lat');
            localStorage.removeItem('lng');
            localStorage.removeItem('gender');
            localStorage.removeItem('logo_img');
            localStorage.removeItem('pnsCnt');
            localStorage.removeItem('nowHash');
		    navigator.app.exitApp();
		}
    }


	function app_pause(){
		//navigator.notification.alert( 'app pausado "pause"', false, "Aviso", 'Ok' );
	}

	function app_resume(){
		//phonon.notif( "Bem vindo de volta", 4000, false );
	}

	/*function exitAppToast() {
        *//*var exitConfirm = phonon.confirm('종료하시겠습니까?', '종료', false, '확인', '취소');
        exitConfirm.on('confirm', function() {
            localStorage.clear();
            navigator.app.exitApp();
        });
        exitConfirm.on('cancel', function() {
            isClick = false;
        });

        setTimeout({ isClick = true; }, 3000);*//*
	}*/

	function setToast() {
	    window.plugins.toast.showWithOptions({
            message: "뒤로(Back)버튼을 한번 더 누르면 종료됩니다.",
            duration: "short",
            position: "bottom",
            addPixelsY: -150,
            styling: {
                opacity: 0.65,
                backgroundColor: '#000000',
                textColor: '#FFFFFF',
                textSize: 20,
                cornerRadius: 0
            }
        });
	}
});