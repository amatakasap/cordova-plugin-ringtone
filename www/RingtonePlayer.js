var exec = require('cordova/exec')

/**
 * Play ringtone
 *
 * @param success {Function}
 * @param error {Function}
 */
exports.play = function (success, error) {
  exec(success, error, 'RingtonePlayer', 'play', [])
}

/**
 * Stop ringtone
 *
 * @param success {Function}
 * @param error {Function}
 */
exports.stop = function (success, error) {
  exec(success, error, 'RingtonePlayer', 'stop', [])
}
