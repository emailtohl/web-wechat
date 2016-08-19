/**
 * 
 */
$(function() {
	var connection = new WebSocket('ws://' + window.location.host + '/web-wechat/yyyService/user');
	connection.onopen = function(e) {
		console.log('打开连接');
		/*var intervalId = setInterval(function() {
			if (connection.readyState != WebSocket.OPEN) {
				clearInterval(intervalId);
				return;
			}
			connection.send('hello');
		}, 500);*/
		var msg = $('#userinfo').val();
		msgObj = JSON.parse(msg);
		msgObj.action = 'register';
		connection.send(JSON.stringify(msgObj));
	};
	
	connection.onmessage = function(e) {
		var m = JSON.parse(e.data);
		if ('ending' == m.action) {
			var users = m.rank;
			var userId = $('#userId').text();
			for (var i = 0; i < users.length; i++) {
				if (userId == users[i].openId) {
					$('#tips').text('你的名次是：' + (i + 1));
				}
			}
		} else if ('running' == m.action) {
			var progress = $('#progress').get(0);
		    if (progress.value <= 100) {
		    	progress.value += 100/20;
		    }
		}
	};
	
	$('#start').on('click', function(e) {
		var i = 5;
		var t = window.setInterval(function() {
			$('#tips').text('ready  ' + i);
			if (i > 0) {
				i--;
			} else {
				$('#tips').text('go !');
				window.clearInterval(t);
			}
		}, 1000);
	});

	
	var myShakeEvent = new Shake({
	    threshold: 15, // optional shake strength threshold
	    timeout: 1000 // optional, determines the frequency of event generation
	});
	
	myShakeEvent.start();
	window.addEventListener('shake', shakeEventDidOccur, false);
	//function to call when shake occurs
	function shakeEventDidOccur (e) {
	    //put your own code here etc.
		var msg = $('#userinfo').val();
		var msgObj = JSON.parse(msg);
		msgObj.action = 'running';
	    connection.send(JSON.stringify(msgObj));
/*	    
	    var progress = $('#progress').get(0);
	    if (progress.value <= 100) {
	    	progress.value += 100/20;
	    }
	    */
	}
	
});