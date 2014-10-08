function ddd(obj, sType) { 
	var oDiv = document.getElementById(obj); 
	if (sType == 'show') { oDiv.style.display = 'block';} 
	if (sType == 'hide') { oDiv.style.display = 'none';} 

	} 
var i=0;
function hide(idx){
	
	
	 
	  if(i%2==0){document.getElementById(idx).style.display = 'block';
	 
	  }else{
		  document.getElementById(idx).style.display = 'none';
	  }
	  return i=i+1;
	  
	}
window.onload=function(){
	var u=document.getElementsByTagName("input"); 
	for(var i=0;i<u.length;i++){
	if(u[i].type=="text"||u[i].type=="password"){
		u[i].className="myInput";
		}
	}
}


function ischeckNum(i)
{
 var num = document.getElementById(i).value;
 if( num )
 {
  if( !isNaN( num ) )
  {
    
  }
  else
  {
   alert('你输入的数据不是数字');
   document.getElementById(i).value="";
   return false;
  }
 }
 else
 {
  alert('需输入内容');
  
 }
}



 