
function viewCount(id) {
    $.ajax({
        type: "POST",
        url: "/viewCount",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id
        }),
        success: function s(response) {
            window.location.href="topicShow/"+id;
        }
    });
}

function likeCount(id,obj) {
    $.ajax({
        type: "POST",
        url: "/likeVote",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "id": id
        }),
        success: function s(response) {
            alert(response.message);
            obj.onclick=null;
        }
    });
}

function post() {
    var id1=document.getElementById('topic_id').value;
    var content1=document.getElementById('replyContent').value;
    if(!content1){
        alert("回复不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "parentid": id1,
            "content": content1,
            "type": 1
        }),
        success: function s(response) {
            document.getElementById('replyContent').value="";
            if(response.code!=200){
                if(response.code==2001){
                    var conf=confirm(response.message);
                    if(conf){
                        openModel();
                    }
                }
            }else{
                alert("回复成功");
                window.location.reload();
            }
        }
    });
}

function openModel() {
    $('#myNewModal').modal({
        keyboard: true
    })

};

function Login() {
    var username=document.getElementById('username').value;
    var password=document.getElementById('password').value;

    $.ajax({
        type: "POST",
        url: "/signinWithTopic",
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "username": username,
            "password": password,
        }),
        success: function s(response) {
            if(response.code!=200){
                    alert(response.message);

            }else{
                alert(response.message);
                document.getElementById('username').value="";
                document.getElementById('password').value="";
                $('#myNewModal').modal('hide');
                $(".modal-backdrop").remove();
                window.location.reload();
            }
        }
    });
};




