var apn = require('apn');

process.env['DEBUG'] = 'apn';

var options = {
		production: false,
		gateway : 'gateway.sandbox.push.apple.com',
		cert: './cert.pem',
		key: './key.pem',
		passphrase: 'secret'
	};

var apnConnection = new apn.Connection(options);


var token = '00b34ce62a397e2fb2f765cfce75a555d5f87f0156ec94e1483ced569776f564';
var myDevice = new apn.Device(token);

var note = new apn.Notification();
note.badge = 1;
note.alert = 'saltfactory 푸시 테스트';
note.payload = {'message': '안녕하세요'};

apnConnection.pushNotification(note, myDevice);