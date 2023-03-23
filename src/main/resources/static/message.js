const msgBox = $('#msg-box');
var msgToggleStatus = false;
function msgToggle() {
    var msgToggle = $('.msg-toggle-btn');
    var modifyToggle = $('.modify-btn');
    if(!msgToggleStatus){
        msgToggle.css("top","230px")
        // msgToggle.css("right","200px")
        //     modifyToggle.css("position","fixed");
        modifyToggle.css("left","700px");
        msgToggleStatus = true;
    }else {
        msgToggle.css("top","0px");
        modifyToggle.css("left","0px");
        msgToggleStatus = false;
    }
    msgBox.toggle(0,'show');
}

let newMsgBtnStatus = false;

$('.new-msg-btn').click(function () {
    $('.msg-user-list-li').removeAttr('style');
    if (newMsgBtnStatus === true) {
        return;
    }
    $('.msg-user-list-box').prepend("<button class='msg-user-search-box-close' onclick='msgSearchBoxClose()' value='<'></button>")
    var msgBox = $('.msg-content-box');
    msgBox.css("display", "none");
    msgBox.after('<div class="msg-user-search-box"><input type="text" class="msg-user-search-bar"  placeholder="검색 할 아이디를 입력해주세요..." onkeyup="msgUserSearchFunc()"></div>');
    var msgSearchBox = $('.msg-user-search-box');
    msgSearchBox.append("<ul class='msg-search-box-ul'></ul>");
    newMsgBtnStatus = true;
});

// 메시지창 없애기
function removeMsgContent(){
    $('.msg-input-con').remove();
    $('.msg-input-btn-con').remove();
}

// 메시지 검색창 끄기
function msgSearchBoxClose() {
    $('.msg-user-search-box').remove();
    $('.msg-user-search-box-close').remove();
    removeMsgContent();
    var msgBox = $('.msg-content-box');
    msgBox.css("display", "block");
    newMsgBtnStatus = false;
}

// 메시지 검색창
function msgUserSearchFunc() {
    var word = $('.msg-user-search-bar').val();
    $.ajax({
        url: "/ajaxMsgUserSearch",
        type: "POST",
        data: {
            "userId": word
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var str = '';
            if (data != null) {
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str += "<li onclick='chatStart(" +data[i].userNo+ ");' class='msg-user-list-result-" + data[i].userNo + "'> " + data[i].userName + "<br><span class='msg-user-list-result-span'>@" + data[i].userId + "</span></li>";
                    }
                    $('.msg-search-box-ul').html(str);
                }
            } else {
                $('.msg-search-box-ul').html(str);
            }
        },
        error: function (request, status, error) {
            console.log("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    });
}

// 메시지 입력 버튼
function msgBtn(userNo1) {
    $.ajax({
        url: "/ajaxMsgSend",
        type: "post",
        data: {
            "msgSendNo" : $('#userNo').val(),
            "userNo1" : userNo1,
            "msgContent":$(".msg-input").val()
        },
        datatype: "json",
        success: function (data) {
            $('.msg-input').val('');
        },
        error: function (request, status, error) {
            alert("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    });
}



function chatStart(userNo1) {
    $('.msg-box').remove();
    if (userNo1 != $('#msg-user-list-id-' + userNo1).val()) {
        $.ajax({
            url:"/ajaxFindByUserNo1",
            type:"post",
            dataType:"json",
            data:{
                "userNo":userNo1
            },
            success:function(data){
                var str = "";
                str += "<li onclick='addChatRoom(" + userNo1 + ")' class='msg-user-list-li' id='msg-user-list-li-" + userNo1 + "'>" + data.userName + "<p>@" + data.userId + "</p>";
                str += "<input type='hidden' value='" + userNo1 + "' id='UserNo1-" + userNo1 + "'></li>";
                $('.msg-user-list-ul').prepend(str);
                $('#msg-user-list-li-' + userNo1).css("background-color", "#F2F3F5");
                $('.msg-content-box').append("<div class='msg-box'></div>");
                msgSearchBoxClose();
                addMsgInputBox(userNo1);
            }
        });
    }else {
        $.ajax({
            url:"/ajaxSearchByChatRoom",
            type:"post",
            dataType:"json",
            data:{
                "userNo1":userNo1,
                "userNo2":$('#userNo').val()
            },
            success:function(data){
                console.log(data);
                $('.msg-user-list-li').removeAttr('style');
                $('.msg-content-box').append("<div class='msg-box'></div>");
                msgSearchBoxClose();
                addMsgInputBox(userNo1);
                addChatRoom(userNo1,data.chatRoomNo);
            }
        })

    }
}

function addMsgInputBox(userNo1){
    $('.msg-input-box').remove();
    var str2 = "";
    str2 += "<div class='msg-input-box' style='display: inline-block; flex-direction: row;float:left;'>";
    str2 +=     "<div style='display:flex; height: 100%; width: 495px; background-color: #F2F3F5;  float:left;'>";
    str2 +=     "<div class='msg-input-con'>";
    str2 +=        "<input type='text' class='msg-input' onKeyPress='javascript:if(event.keyCode==13) {msgBtn("+userNo1+")}'>";
    str2 +=            "<button type='button' class='msg-input-btn' onClick='msgBtn("+userNo1+")'></button>";
    str2 +=     "</div>"
    str2 +=     "<div class='msg-input-btn-con'><button class='msg-room-close' onclick='chatRemove("+$('#msg-user-list-id-'+userNo1).val()+","+userNo1+")'>나가기</button></div>";
    str2 +=    "</div>";
    str2 +="</div>";
    $('.msg-area').append(str2);
}

function chatRemove(chatRoomNo, userNo1){
    $.ajax({
        url:"/ajaxChatRemove",
        data:{
            "userNo1":userNo1,
            "chatRoomNo":chatRoomNo
        },
        type:"post",
        dataType:"json",
        success:function (data){
            if(confirm("채팅에서 나가시겠습니까?")){
                removeMsgContent();
                $('#msg-user-list-li-'+userNo1).remove();
                $('.msg-input-box').remove();
                $('.msg-content-box').append("<div class='msg-box'></div>");
                $('.msg-area').append('<div class="msg-input-box" style="display: inline-block; flex-direction: row;float:left;"><div style="display:flex; height: 100%; width: 495px; background-color: #F2F3F5;  float:left;"></div></div>');
                $('.msg-box').remove();
                $('#msg-user-list-li-'+chatRoomNo).remove();
            }
        },
        error: function (request, status, error) {
            console.log("code : " + request.status + "\n" + " message : " + request.responseText + "\n" + "error: " + error);
        }
    });
}

// 페이지 로드시 채팅룸 로딩
function loadingChatRoom() {
    $.ajax({
        url: "/ajaxLoadChatRoom",
        data: {
            "userNo": $("#userNo").val()
        },
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var str = "";
                    str += "<li onclick='addChatRoom(" + data[i].userNo+","+data[i].chatRoomNo + ")' class='msg-user-list-li' id='msg-user-list-li-"+data[i].chatRoomNo+"'>" + data[i].userName + "<p>@"+data[i].userId+"</p><input type='hidden' value='"+data[i].userNo+"' id='msg-user-list-id-"+data[i].userNo+"'></li>";
                    $('.msg-user-list-ul').append(str);
                    $('.msg-input-box').remove();
                    var str2 = "";
                    str2 += "<div class='msg-input-box' style='display: inline-block; flex-direction: row;float:left;'>";
                    str2 +=     "<div style=' height: 100%; width: 495px; background-color: #F2F3F5;  float:left;'>";
                    str2 +=    "</div>";
                    str2 +="</div>";
                    $('.msg-area').append(str2);
                }
            }
        }
    });
}
let timer = setInterval(function(){},1000);
function addChatRoom(userNo,chatRoomNo){
    clearInterval(timer);
    $('.msg-content-box').scrollTop($('.msg-content-box')[0].scrollHeight);
    $('.msg-user-list-li').removeAttr('style');
    $('#msg-user-list-li-'+chatRoomNo).css("background-color","#F2F3F5");
    $('#msg-user-list-li-'+chatRoomNo).css("pointer-event","none");
    removeMsgContent();
    timer = setInterval(function () {
        $('.receive-msg-area-container').remove();
        $('.send-msg-area-container').remove();
    $.ajax({
        url:"/ajaxChatRoom",
        type:"post",
        dataType:"json",
        data:{
            "chatRoomNo" : chatRoomNo,
        },
        async:false,
        success:function(data){
            console.log(data);
            var str = "";

            // if(data.length > 0){
            for(var a = 0; a < data.length; a++){
                    if(parseInt($('#userNo').val()) !== data[a].msgSendNo){
                        str += "<div class='receive-msg-area-container'>";
                        str += "<div class='receive-msg-area'>";
                        str += "<ul>";
                        str += "<li>" + data[a].msgContent + "</li>";
                        str += "<span class='receive-msg-p'>"+moment(data[a].sendTime).format("MM-DD HH:mm")+"</span><br>";
                        str += "</ul>";
                        str += "</div>";
                        str += "</div>";
                    }else {
                        str += "<div class='send-msg-area-container'>";
                        str += "<div class='send-msg-area'>";
                        str += "<ul>";
                        str += "<span class='receive-msg-p'>"+moment(data[a].sendTime).format("MM-DD HH:mm")+"</span><li>" + data[a].msgContent + "</li><br>";
                        str += "</ul>";
                        str += "</div>";
                        str += "</div>";
                    }
                }
            // }
            let msgBox = $('.msg-box');
            msgBox.append(str);

        }
    })
    }, 1000);
    addMsgInputBox(userNo);
    $('.msg-content-box').scrollTop($('.msg-content-box')[0].scrollHeight);

}



