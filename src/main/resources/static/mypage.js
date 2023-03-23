var profileStatus = false;
var name = $('.profile-name-h1').text();
var letter = $('.profile-letter-span').text();
var profileImg = $('.profile-img');
var modal = $('.profile-modal');


// 마우스 인 이벤트
profileImg.mouseover(function () {
    profileImg.append("<span class='profile-img-modify-span'>프로필 수정</span>");
});
// 마우스 아웃 이벤트
profileImg.mouseout(function () {
    $('.profile-img-modify-span').remove();
});


//모달창
profileImg.click(function () {
    modal.css("display", "block");
    modal.classList.toggle('show');
});
//모달창 닫기
$('.profile-modal_close').click(function () {
    modal.css("display", "none");
});


//이미지 미리보기
var sel_file;

$(document).ready(function () {
    $("#file").on("change", handleImgFileSelect);
});

function handleImgFileSelect(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    var reg = /(.*?)\/(jpg|jpeg|png|bmp)$/;

    filesArr.forEach(function (f) {
        if (!f.type.match(reg)) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }

        sel_file = f;

        var reader = new FileReader();
        reader.onload = function (e) {
            console.log(e.target.result);
            $(".upload-pic-miri").attr("src", e.target.result);
        }
        reader.readAsDataURL(f);
    });
}

//파일 업로드
function fn_submit() {

    var form = new FormData();
    form.append("file", $("#file")[0].files[0]);

    $.ajax({
        url: "/ajaxFileUpload"
        , type: "POST"
        , processData: false
        , contentType: false
        , data: form
        , success: function (response) {
            alert("저장되었습니다.");
            modal.css("display", "none");
            history.go(0);
        }
        , error: function (jqXHR) {
            alert(jqXHR.responseText);
        }
    });
}

// 프로필 수정 시작 버튼
$(".modify-btn").click(function () {

    if (!profileStatus) {
        var str = "<input type='text' value='" + $('.profile-name-h1').html() + "' class='profile-name-input' spellcheck='false'><button type='button' onclick='ajaxProfileModify();' value='저장' class='modify-submit-btn'>수정</button><button type='button' onclick='modifyClose();' value='저장' class='modify-submit-btn' id='modify-close-btn'>취소</button>";
        var str2 = "<textarea type='text' class='profile-letter-input' spellcheck='false'></textarea>";
        $('.profile-name-h1').remove();
        $('.profile-name').append(str);
        $('.profile-letter').append(str2);
        $('.profile-letter-input').val($('.profile-letter-span').html());
        $('.profile-letter-span').remove();
        profileStatus = true;
    }
});

function modifyClose() {
    $('.profile-name-input').remove();
    $('.modify-submit-btn').remove();
    $('.profile-letter-input').remove();
    $('.modify-close-btn').remove();
    var str = "<h1 class='profile-name-h1'>" + name + "</h1>";
    var str2 = "<span class='profile-letter-span'>" + letter + "</span>";
    $('.profile-name').append(str);
    $('.profile-letter').append(str2);
    profileStatus = false;
}

function ajaxProfileModify() {
    profileStatus = false;
    $.ajax({
        url: "/ajaxProfileModify",
        type: "post",
        dataType: "json",
        data: {
            "userNo": $('#userNo').val(),
            "userName": $('.profile-name-input').val(),
            "userLetter": $('.profile-letter-input').val()
        },
        success: function (data) {
            $('.modify-submit-btn').remove();
            $('.profile-name-input').remove();
            $('.profile-letter-input').remove();
            str = "<h1 class='profile-name-h1'>" + data.userName + "</h1>";
            str2 = "<span class='profile-letter-span'>" + data.userProfileLetter + "</span>";
            $('.profile-name').append(str);
            $('.profile-letter').append(str2);
            str3 = "<img src='../img/abstract-user-flat-3.svg' style='width: 30px; height: 30px; border-radius: 50%' />";
            $('#dropdownMenuButton1').text("  " + data.userName + "  ");
            $('#dropdownMenuButton1').prepend(str3);
        },
        error: function (request, status, error) {
            alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    });
}


// 아코디언 메뉴
$(".que").click(function () {
    $(this).next(".anw").stop().slideToggle(300);
    $(this).toggleClass('on').siblings().removeClass('on');
    $(this).next(".anw").siblings(".anw").slideUp(300); // 1개씩 펼치기
});

//  포인트 잔량 정규 표현식
var pointVal = document.querySelector(".point-val").value;
document.querySelector(".point-val-re").innerHTML = pointVal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");

// 포인트 충전 내역 정규표현식
var pointVal2 = document.querySelectorAll(".pointRecord-val");
for (var a = 0; a < pointVal2.length; a++) {
    document.querySelectorAll(".pointRecord-val-re")[a].innerHTML = pointVal2[a].value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + " Mango";
}

// 모달
function modal(id) {
    var zIndex = 9999;
    var modal = document.getElementById(id);

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('#modal_close_btn').addEventListener('click', function () {
        bg.remove();
        modal.style.display = 'none';
    });


    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,

        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)'
    });
}

// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
Element.prototype.setStyle = function (styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};

document.getElementById('popup_open_btn').addEventListener('click', function () {
    // 모달창 띄우기
    modal('my_modal');
    $('#point-add-input').val('');
});
document.getElementById('popup_open_btn-2').addEventListener('click', function () {
    // 모달창 띄우기
    modal('my_modal-2');
    $('#point-refund-input').val('');

});

// 포인트 충전
function pointAddFunc() {
    let pointData = {
        "userNo": parseInt($('#userNo').val()),
        "pointVal": parseInt($('#point-add-input').val()),
    };
    if (pointData.pointVal < 0) {
        pointData = null;
    }
    $.ajax(
        {
            type: "POST",
            url: "/ajaxAddPoint",
            data: pointData,
            success: function (data) {
                $('.point-val-re').text(data.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
            },
            error: function () {
                alert("올바른 값을 입력해주세요.");
            }
        }
    )
    $('#modal_close_btn').trigger("click");
}

// 포인트 환불
function pointRefundFunc() {
    let pointData = {
        "userNo": parseInt($('#userNo').val()),
        "pointVal": parseInt($('#point-refund-input').val()),
        "pointCurrentVal": parseInt($('#point-val-current').val())
    };
    if (pointData.pointVal > pointData.pointCurrentVal) {
        pointData = null;
    }
    $.ajax(
        {
            type: "POST",
            url: "/ajaxRefundPoint",
            data: pointData,
            success: function (data) {
                $('.point-val-re').text(data.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","));
            },
            error: function (request, status, error) {
                if (request.status === 400) {
                    alert("올바른 값을 입력해주세요.");
                } else if (request.status === 500) {
                    alert("현재 Mango 보다 작은 값을 입력해주세요.");
                }

            }
        }
    )
    $('.add-btn')[3].click();

}


// 음악 정보 api 로 가져오기

test = $('#likeTag').val();
var request = new XMLHttpRequest();
request.open('GET', 'https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=45ac2dc0a0b1bad3966314cc210f8c49&artist=cher&track=believe&format=json');
request.send();
request.onload = function () {
    var songInfo = JSON.parse(request.response).track;
    document.querySelector("#songName").innerHTML = songInfo.name;
    document.querySelector("#songArtist").innerHTML = songInfo.artist.name;
}