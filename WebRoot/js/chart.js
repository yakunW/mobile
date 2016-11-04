
  function table1(total,table_x,table_y,thickness,table_width,all_width,all_height,table_type){
  var tmdColor1 = new Array();
tmdColor1[0] = "#d1ffd1";
  tmdColor1[1] = "#ffbbbb";
  tmdColor1[2] = "#ffe3bb";
  tmdColor1[3] = "#cff4f3";
  tmdColor1[4] = "#d9d9e5";
  tmdColor1[5] = "#ffc7ab";
  tmdColor1[6] = "#ecffb7";
var tmdColor2 = new Array();
tmdColor2[0] = "#00ff00";
  tmdColor2[1] = "#ff0000";
  tmdColor2[2] = "#ff9900";
  tmdColor2[3] = "#33cccc";
  tmdColor2[4] = "#666699";
  tmdColor2[5] = "#993300";
  tmdColor2[6] = "#99cc00";
var tb_color = new Array(tmdColor1,tmdColor2);
var line_color = "#69f";
var left_width = 70;
var length = thickness/2;
var total_no = total[0].length;
var temp1 = 0;
  var temp2,temp4,temp4;
for(var i = 0;i<total_no;i++)
  {
  if(temp1<total[0][i])
  {
  temp1 = total[0][i];
  }
  }
temp1 = parseInt(temp1.toString());
if(temp1>9)
  {
  temp2 = temp1.toString().substr(1,1)
if(temp2>4)
  {
  temp3 = (parseInt((temp1/(Math.pow(10,(temp1.toString().length-1)))).toString())+1)*Math.pow(10,(temp1.toString().length-1));
  }
  else
  {
  temp3 = (parseInt((temp1/(Math.pow(10,(temp1.toString().length-1)))).toString())+0.5)*Math.pow(10,(temp1.toString().length-1));
  }
  }
  else
  {
  if(temp1>4)
  temp3 = 10;
  else
  temp3 = 5;
  }
temp4=temp3
  document.write("<!--[if gte vml 1]><v:rect id='_x0000_s1027' alt='' style='position:absolute;left:" + (table_x+left_width) + "px;top:" + table_y + "px;width:" + all_width + "px;height:" + all_height + "px;z-index:-1' fillcolor='#9cf' stroked='f'><v:fill rotate='t' angle='-45' focus='100%' type='gradient'/></v:rect><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width) + "px," + (table_y+all_height) + "px' to='" + (table_x+all_width+left_width) + "px," + (table_y+all_height) + "px'/><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width) + "px," + table_y + "px' to='" + (table_x+left_width) + "px," + (table_y+all_height) + "px'/><![endif]-->");
  switch (table_type)
  {
  case "A":
var table_space = (all_width-table_width*total_no)/total_no;
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+length) + "px,"+ table_y + "px' to='" + (table_x+left_width+length) + "px," + (table_y+all_height-length) + "px' strokecolor='" + line_color + "'/><![endif]-->");
for(var i=0;i<=all_height-1;i+= all_height/5)
  {
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width)+ "px," + (table_y+all_height-length-i) + "px' to='" + (table_x+left_width+length) + "px," + (table_y+all_height-i) +"px' strokecolor='" + line_color + "'/><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+length) + "px," + (table_y+all_height-length-i) + "px' to='" + (table_x+all_width+left_width) + "px," + (table_y+all_height-length-i) + "px' strokecolor='" + line_color + "'/><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+(left_width-15)) + "px," + (table_y+i) + "px' to='" + (table_x+left_width) + "px," + (table_y+i) + "px'/><![endif]-->");
  document.write("<!--[if gte vml 1]>");
  document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + table_x + "px;top:" + (table_y+i) + "px;width:" + left_width + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='right'>" + temp4 + "</td></tr></table></v:textbox></v:shape><![endif]-->");
  temp4 = temp4-temp3/5;
  }
  for(var i=0;i<total_no;i++)
  {
var temp_space = table_x + left_width + table_space / 2 + table_space * i + table_width * i;
  document.write("<v:rect id='_x0000_s1025' alt='' style='position:absolute;left:");
  document.write(temp_space);
  document.write("px;top:");
  document.write(table_y + all_height * (1 - (total[0][i] / temp3)));
  document.write("px;width:" + table_width + "px;height:" + all_height * (total[0][i] / temp3) + "px;z-index:1' fillcolor='" + tb_color[1][i] + "'>");
  document.write("<v:fill color2='" + tb_color[0][i] + "' rotate='t' type='gradient'/>")
  document.write("<o:extrusion v:ext='view' backdepth='" + thickness + "pt' color='" + tb_color[1][i] + "' on='t'/>");
  document.write("</v:rect>");
  document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + temp_space + "px;top:" + (table_y+all_height*(1-(total[0][i]/temp3))-table_width) + "px;width:" + (table_space+15) + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='center'>" + total[0][i] + "</td></tr></table></v:textbox></v:shape>");
  document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + (temp_space-table_space/2) + "px;top:" + (table_y+all_height+1) + "px;width:" + (table_space+table_width) + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='center'>" + total[1][i] + "</td></tr></table></v:textbox></v:shape>");
  }
break;
  case "B":
  var table_space = (all_height - table_width * total_no) / total_no;
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+length) + "px," + (table_y+all_height-length) + "px' to='" + (table_x+left_width+all_width) + "px," + (table_y+all_height-length) + "px' strokecolor='" + line_color + "'/><![endif]-->");
  for(var i=0;i<=all_width-1;i +=all_width/5)
  {
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+i) + "px," + (table_y+all_height-length) + "px' to='" + (table_x+left_width+length+i) + "px," + (table_y+all_height) + "px' strokecolor='" + line_color + "'/><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+length+i) + "px," + (table_y+all_height-length) + "px' to='" + (table_x+left_width+length+i) + "px," + table_y + "px' strokecolor='" + line_color + "'/><![endif]-->");
  document.write("<!--[if gte vml 1]><v:line id='_x0000_s1027' alt='' style='position:absolute;left:0;text-align:left;top:0;flip:y;z-index:-1' from='" + (table_x+left_width+i+all_width/5) + "px," + (table_y+all_height) + "px' to='" + (table_x+left_width+i+all_width/5) + "px," + (table_y+all_height+15) + "px'/><![endif]-->");
  document.write("<!--[if gte vml 1]>");
  document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + (table_x+left_width+i+all_width/5-left_width) + "px;top:" + (table_y+all_height) + "px;width:" + left_width + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='right'>" + temp4 + "</td></tr></table></v:textbox></v:shape><![endif]-->");
  temp4 = temp4 - temp3 / 5;
  }
for(var i=0;i<total_no;i++)
  {
  var temp_space = table_space/2 + table_space * i + table_width * i;
  document.write("<v:rect id='_x0000_s1025' alt='' style='position:absolute;left:");
  document.write(table_x + left_width);
  document.write("px;top:");
  document.write(table_y + temp_space);
  document.write("px;width:" + all_width * (total[0][i] / temp3) + "px;height:" + table_width + "px;z-index:1' fillcolor='" + tb_color[1][i] + "'>");
  document.write("<v:fill color2='" + tb_color[0][i] + "' rotate='t' angle='-90' focus='100%' type='gradient'/>");
  document.write("<o:extrusion v:ext='view' backdepth='" + thickness + "pt' color='" + tb_color[1][i] + "' on='t'/>");
  document.write("</v:rect>");
  document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + (table_x + left_width + all_width * (total[0][i] / temp3) + thickness / 2) + "px;top:" + (table_y + temp_space) + "px;width:" + (table_space + 15) + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='center'>" + total[0][i] + "</td></tr></table></v:textbox></v:shape>");
document.write("<v:shape id='_x0000_s1025' type='#_x0000_t202' alt='' style='position:absolute;left:" + table_x + "px;top:" + (table_y + temp_space) + "px;width:" + left_width + "px;height:18px;z-index:1'>");
  document.write("<v:textbox inset='0px,0px,0px,0px'><table cellspacing='3' cellpadding='0' width='100%' height='100%'><tr><td align='right'>" + total[1][i] + "</td></tr></table></v:textbox></v:shape>");
  }
}
}
