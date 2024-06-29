<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="scrollable-container">
  <button class="scrollable-command-button previous-button material-button">
    <span class="material-icons-round md-18">arrow_back_ios</span>
  </button>

  <div class="scrollable-elements-container" id="${param.id}">
      <h6 class="rubik-400" id="loading-latest-products-text">${param["loading-text"]}</h6>
  </div>

  <button class="scrollable-command-button next-button material-button">
    <span class="material-icons-round md-18">arrow_forward_ios</span>
  </button>
</div>
