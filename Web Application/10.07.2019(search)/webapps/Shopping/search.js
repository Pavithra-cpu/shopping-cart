var options = new Array(
  new Array("ITEMNAME","Equals","="),
  new Array("ITEMNAME","Not Equals","!="),
  new Array("ITEMNAME","Starts with","LIKE_%"),
  new Array("ITEMNAME","Ends with","LIKE%_"),
  new Array("ITEMNAME","Contains","LIKE%%"),
  new Array("QUANTITY","Greater than",">"),
  new Array("QUANTITY","Greater than equal",">="),
  new Array("QUANTITY","Less than","<"),
  new Array("QUANTITY","Less than equal","<="),
  new Array("PRICE","Greater than",">"),
  new Array("PRICE","Greater than equal",">="),
  new Array("PRICE","Less than","<"),
  new Array("PRICE","Less than equal","<=")
);
function chooseSelect(attribute,constraint,options) {
  (attribute.style || attribute).visibility = "visible";
  attribute.onchange = function() {
    var attrToChange = attribute.options[attribute.selectedIndex].value;
    constraint.options.length = 0;
    for (i = 0; i < options.length; i++) {
      if (options[i][0] == attrToChange) {
        constraint.options[constraint.options.length] = new Option(options[i][1],options[i][2]);
      }
    }
    constraint.options[0].selected = true;
  }
  attribute.onchange();
}
function select(){
   var element=document.forms['searchform'].elements;
   chooseSelect(element['attribute1'],element['constraint1'],options);
   chooseSelect(element['attribute2'],element['constraint2'],options);
}
