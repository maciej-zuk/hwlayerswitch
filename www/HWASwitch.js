var exec = require('cordova/exec');

exports.switchHWA = function(arg0, success, error) {
    exec(success, error, "HWASwitch", "switchHWA", [arg0]);
};
