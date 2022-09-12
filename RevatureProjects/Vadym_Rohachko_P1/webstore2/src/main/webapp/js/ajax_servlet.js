/*
$(document).ready(function () {
  // Listen to submit event on the <form> itself!
  $('#main').submit(function (e) {

    e.preventDefault();

    var categoryId = $("categoryId").val();
    var productId = $("productId").val();

    $.post("/webstore/invent", {
      categoryId: categoryId,
      productId: productId
    }).complete(function() {
        console.log("Success");
      });
  });
});
*/

/*
$('.card__button_type_submit').on('click', 'button', function(){
    console.log("Submit");
});
*/

/*
/*
				+ "<script type=\"text/JavaScript\">"
				+ "$(document).ready(function () {"
                + "$('#main').submit(function (e) {"
                + "e.preventDefault();"
                + "var formData = $(this).serialize();"
                + "$.post(\"/webstore/invent\", formData).done(function() {"
                +   "console.log(\"Success\");"
                + "});"
                + "});"
                + "});"
				+ "</script>"
				*/	
				
				