$(function() {
	
	var connection = new WebSocket('ws://' + window.location.host + '/web-wechat/yyyService/monitor');
	connection.onopen = function(e) {
		console.log('打开连接');
		/*var intervalId = setInterval(function() {
			if (connection.readyState != WebSocket.OPEN) {
				clearInterval(intervalId);
				return;
			}
			connection.send('hello');
		}, 500);*/
	};

	connection.onmessage = function(e) {
		var o = JSON.parse(e.data);
		switch(o.action) {
			case 'register':
				addUser(o);
				break;
			case 'running':
				run(o);
				break;
			case 'exit':
				remove(o);
				break;
			case 'ending':
				showRank(o);
				break;
			default:
		}
	};
	
	
	function addUser(o) {
		$('<div id="' + o.openId + '" style="width:50px;position:relative"><img src="'+ o.headimgurl + '" height="50px"></img><span>' + o.nickname + '</span><span class="status"></span></div>').appendTo($('#paodao'));
	}
	
	function remove(o) {
		var id = o.openId;
		$('#' + id).remove();
	}
	
	function run(o) {
		var stepSize = 15;
		var count = $('#' + o.openId).get(0).style.left;
		count = parseInt(count);
		if (!count) {
			count = stepSize;
		} else {
			count += stepSize;
		}
		if (count > 20 * stepSize) {
			$('#' + o.openId).find('span.status').text('done');
		} else {
//			$('#' + o.openId).get(0).style.left = count + 'px';
			$('#' + o.openId).animate({left : count + 'px'});
		}
	}
	
	function showRank(o) {
		var res = '';
		var users = o.rank;
		for (var i = 0; i < users.length; i++) {
			res += '名次：' + (i + 1) + '  昵称： ' + users[i].nickname + '<br>';
		}
		$('#tips').html(res);
	}
	
	
	$('#start').on('click', function(e) {
		var i = 5;
		var t = window.setInterval(function() {
			$('#tips').text('ready  ' + i);
			if (i > 0) {
				i--;
			} else {
				$('#tips').text('go !');
				window.clearInterval(t);
				connection.send('start');
			}
		}, 1000);
	});
});
