var URL = {

	syn : '/UAServer1/servlet/Syn',
	sendMessage : '/UAServer1/servlet/SendMessage',
	getMessage : '/UAServer1/servlet/GetMessage',

};

var loginIE = 0;

var userId = '';

function loginUser() {

	var userIdEle = document.getElementById("userId");

	var loginButton = document.getElementById("login");

	userId = userIdEle.value;

	if (loginIE == 0) {

		userIdEle.disabled = true;

		loginButton.value = "下线";

		loginIE = 1;

		sendData(URL.syn, "userId=" + userId, synMethod);
	} else {
		loginButton.value = "登录";

		userIdEle.disabled = false;

		loginIE = 0;
	}

}

function synMethod(data) {

	
	if (loginIE == 1) {
		
		if (data.code == 1) {
			sendData(URL.getMessage, "userId=" + userId, getMessageMethod);
		}
		
		sendData(URL.syn, "userId=" + userId, synMethod);
	}

}

function getMessageMethod(dataArray) {

	for ( var data in dataArray) {

		var contentDocument = document.getElementById("content");

		var trNode = document.createElement("tr");

		var tdHeadNode = document.createElement("td");
		
		var tdBodyNode = document.createElement("td");
		
		var tdNode = document.createElement("td");
		tdNode.innerHTML = " ";
		
		var liNodeBody = document.createElement("div");
		liNodeBody.innerHTML = dataArray[data].message;

		var liNodeHead = document.createElement("div");
		liNodeHead.innerHTML = "<<--" + dataArray[data].fromUserId;

		tdBodyNode.appendChild(liNodeBody);
		tdHeadNode.appendChild(liNodeHead);

		trNode.appendChild(tdNode);
		trNode.appendChild(tdBodyNode);
		trNode.appendChild(tdHeadNode);
		
		trNode.style.color = 'red';
		contentDocument.appendChild(trNode);

	}

}

function sendMessage() {

	var messageEle = document.getElementById("message");

	var message = messageEle.value;
	
	var receiveId = document.getElementById("receiveId").value;

	var sendContent = "receiveId=" + receiveId + "&message=" + message
			+ "&userId=" + userId;

	sendData(URL.sendMessage, sendContent, sendMessageMethod);

}

function sendMessageMethod(data) {

	if (data.code == "0001") {

		var messageEle = document.getElementById("message");

		var message = messageEle.value;

		messageEle.value = '';

		var contentDocument = document.getElementById("content");

		var trNode = document.createElement("tr");

		var tdHeadNode = document.createElement("td");
		var tdBodyNode = document.createElement("td");
		
		var liNodeBody = document.createElement("div");
		liNodeBody.innerHTML = message;

		var liNodeHead = document.createElement("div");
		liNodeHead.innerHTML = userId + "-->>";

		tdHeadNode.appendChild(liNodeHead);
		tdBodyNode.appendChild(liNodeBody);

		trNode.appendChild(tdHeadNode);
		trNode.appendChild(tdBodyNode);
		trNode.style.color = "green";
		contentDocument.appendChild(trNode);

	} else {

		alert("send message is fail, dis: " + data.dis);

	}

}

function sendData(url, data, methodOK) {

	var xmlhttp;

	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			var content = eval("(" + xmlhttp.responseText + ")");

			methodOK(content);
		}
	};
	xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Connection", "keep-alive");
	
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=utf-8");
	xmlhttp.send(data);

}
