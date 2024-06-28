export { getAjaxRequestObject };

/**
 * Gets an AJAX request object.
 * @returns {XMLHttpRequest|ActiveXObject|null} The AJAX request object or null if not supported.
 */
function getAjaxRequestObject(){
    var request = null;
    try {
        // Firefox 1+, Chrome 1+, Safari 1.2+, IE7+, Opera 8+, Edge 12+
        request = new XMLHttpRequest();
    } catch (e) {
        // IE 6 and older
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Your browser does not support AJAX!");
                return null;
            }
        }
    }
    return request;
}
