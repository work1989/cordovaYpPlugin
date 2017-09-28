var exec = require('cordova/exec');
exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "cordovaYpNewLand", "coolMethod", [arg0]);
};
exports.print = function(arg0, success, error) {
    exec(success, error, "cordovaYpNewLand", "print", [arg0]);
};
exports.scan = function(arg0, success, error) {
    exec(success, error, "cordovaYpNewLand", "scan", [arg0]);
};
exports.getSer = function(arg0, success, error) {
    exec(success, error, "cordovaYpNewLand", "getSer", ["getSer"]);
};


