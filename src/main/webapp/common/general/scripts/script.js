export { getAjaxRequestObject, getBaseOriginName, resolveResource };

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

/**
 * Gets the base origin name.
 * @returns {String} The base origin name.
 */
function getBaseOriginName(){
    return `${window.location.origin}/seedify_war`;
}

/**
 * Resolves a resource URL to its actual URL.
 * @param {String} resourceUrl The resource URL.
 * @returns {Object} The resolution object.
 */
function resolveResource(resourceUrl) {
    let resolution = {
        image: null
    }

    if (resourceUrl.startsWith("image/")) {
        const resourceParams = resourceUrl.replace("image/", "");
        resolution.image = `${getBaseOriginName()}/resources-servlet?${resourceParams}`;
    }

    return resolution;
}