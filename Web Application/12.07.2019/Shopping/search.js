//function chooseSelect(attribute,constraint,options) {
function select() {
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
  //var element=document.forms['searchform'].elements;
  var attribute=document.getElementsByClassName("attribute");
  var constraint=document.getElementsByClassName("constraint");
  var attrToChange = attribute[0].options[attribute.selectedIndex].value;
  constraint[0].options.length = 0;
  for (i = 0; i < options.length; i++) {
    if (options[i][0] == attrToChange) {
      constraint[0].options[constraint[0].options.length] = new Option(options[i][1],options[i][2]);
    }
  }
  constraint[0].options[0].selected = true;
}
  //attribute.onchange();
//}
