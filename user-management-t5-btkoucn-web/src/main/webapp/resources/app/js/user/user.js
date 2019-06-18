/*
 * 初期化
 * 更新・削除ボタンの処理
 */
$(function() {
	$("#updateButton").click(function(){
        var userId=$('input:radio:checked').val();
        if(userId==null){
            alert("更新行を選択してください");
            return false;
        }
        else{
        	$("#updateUserId").val(userId);
        	$("#updateForm").submit();
        }          
     });
	$("#deleteButton").click(function(){
        var userId=$('input:radio:checked').val();
        if(userId==null){
            alert("削除行を選択してください");
            return false;
        }
        else{
        	$("#deleteUserId").val(userId);
        	$("#deleteForm").submit();
        }          
     });
}); 
