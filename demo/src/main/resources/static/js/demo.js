/**
 * 
 */
var cur;
$('tbody tr').on('click', function() {
	$('tbody tr').removeClass('success')
	$(this).addClass('success')
	cur = $(this).attr("id");
})

$('#judge').on('click', function() {

	$.ajax({
		url : '/autojudge',
		type : 'get',
		dataType : 'text',
		data : {
			"id" : cur
		},
		success : function(data) {
			if (data == "OK") {
				$("#" + cur).children("#state").text("通过")
			} else {
				$("#" + cur).children("#state").text("未通过")
			}

		}
	});
})

$('#delete-project-btn').on('click', function() {
	if (cur != null) {
		if(window.confirm('确定删除该项目？')) {
			$.ajax({
				url : '/index/'+cur,
				type : 'delete',
				dataType : 'text',	
				success: function(data) {
					if (data == "OK") {
						location.reload();
					} else {
						alert("删除失败");
					}
				}
			});
	     }
	} else {
		alert("请选择要删除的项目");
	}
	
})


$('#delete-person-btn').on('click', function() {
	if (cur != null) {
		if(window.confirm('确定删除该教师？')) {
			$.ajax({
				url : '/people/'+cur,
				type : 'delete',
				dataType : 'text',	
				success: function(data) {
					if (data == "OK") {
						location.reload();
					} else {
						alert("删除失败");
					}
				}
			});
	     }
	} else {
		alert("请选择要删除的人员");
	}
	
})

$('#delete-fact-btn').on('click', function() {
	if (cur != null) {
		if(window.confirm('确定删除该事实？')) {
			$.ajax({
				url : '/fact/'+cur,
				type : 'delete',
				dataType : 'text',	
				success: function(data) {
					if (data == "OK") {
						location.reload();
					} else {
						alert("删除失败");
					}
				}
			});
	     }
	} else {
		alert("请选择要删除的事实");
	}
	
})


$('#edit-person').on('click', function() {
	if (cur != null) {
		$.ajax({
			url : '/searchPerson/'+cur,
			type : 'get',
			dataType : 'text',	
			success: function(data) {
				var obj = JSON.parse(data);
				$("#edit-name").val(obj.name);
				$("#edit-college").val(obj.college);
				$("#edit-age").val(obj.age);
				$("#edit-sex").val(obj.sex);
				$("#edit-title").val(obj.title);
			
				$('#person-modal').modal();
			}
		});
	} else {
		alert("请选择要编辑的教师");
	}
	
})


$('#edit-fact').on('click', function() {
	if (cur != null) {
		$("#edit-factName").val($("#"+cur).children('td').eq(0).text());
		$("#edit-factDesc").val($("#"+cur).children('td').eq(1).text());
		
		$('#fact-modal').modal();
	} else {
		alert("请选择要编辑的事实");
	}
	
})

$('#edit-save').on('click', function() {
		$.ajax({
			url : '/saveEditPerson',
			type : 'get',
			dataType : 'text',	
			data : {
				"id" : cur,
				"name":$("#edit-name").val(),
				"college":$("#edit-college").val(),
				"age":$("#edit-age").val(),
				"sex":$("#edit-sex").val(),
				"title":$("#edit-title").val()
			},
			success: function(data) {
				if (data == "OK") {
					location.reload();
				} else {
					alert("修改失败");
				}
			}
		});	
})

$('#edit-project').on('click', function() {
	if (cur != null) {
		$.ajax({
			url : '/searchProject/'+cur,
			type : 'get',
			dataType : 'text',	
			success: function(data) {
				var obj = JSON.parse(data);
				console.log(obj);
				$("#edit-projectName").val(obj.projectName);
				$("#edit-des").val(obj.description);
				$("#edit-subject").val(obj.subject.id);
				$("#edit-person").val(obj.person.id);
				$("#edit-datac").val(obj.date);
				$("#read-time").val(obj.date);

				
				$('#project-modal').modal();
			}
		});
	} else {
		alert("请选择要编辑的项目");
	}
	
})

$('#edit-project-save').on('click', function() {
		$.ajax({
			url : '/saveEditProject',
			type : 'get',
			dataType : 'text',	
			data : {
				"id" : cur,
				"projectName":$("#edit-projectName").val(),
				"description":$("#edit-des").val(),
				"subjectId":$("#edit-subject").val(),
				"personId":$("#edit-person").val(),
				"date":$("#edit-datac").val(),
			},
			success: function(data) {
				if (data == "OK") {
					location.reload();
				} else {
					alert("修改失败");
				}
			}
		});	
})



$('#addSubject').on('click', function() {
	var rules = "";
	$('input[name="Rules"]:checked').each(function(){ 
		rules = rules + $(this).val() + ",";
		}); 
	console.log(rules);
	$.ajax({
		url : '/addSubject',
		type : 'post',
		dataType : 'text',	
		data : {
			"name":$("#subject-name").val(),
			"rules":rules,
		},
		success: function(data) {
			if (data == "OK") {
				location.reload();
			} else {
				alert("增加失败");
			}
		}
	});	

})



$('#delete-subject-btn').on('click', function() {
	if (cur != null) {
		if(window.confirm('确定删除该专题？')) {
			$.ajax({
				url : '/subject/'+cur,
				type : 'delete',
				dataType : 'text',	
				success: function(data) {
					if (data == "OK") {
						location.reload();
					} else {
						alert("删除失败");
					}
				}
			});
	     }
	} else {
		alert("请选择要删除的专题");
	}
	
})


$('#edit-subject').on('click', function() {
	if (cur != null) {
		$("#edit-subjectName").val($("#"+cur).children('td').eq(0).text());
		var rules = $("#"+cur).children('td').eq(1).text().split(" ");
		var max = rules.length - 1;
//		$('input[name="RulesE"]').each(function(){ 
//			$(this).attr("checked", true);
//			
//			}); 
//		
		
		$('input[name="RulesE"]').each(function(){ 
			for (var i = 0; i < max; i++)
			{
				if($(this).val() == rules[i]) {
					$(this).attr("checked", true);
				} 
			}
			}); 
		$('#subject-modal').modal();
	} else {
		alert("请选择要编辑的项目");
	}
})


$('#edit-save-subject').on('click', function() {
	var rules = "";
	$('input[name="RulesE"]:checked').each(function(){ 
		rules = rules + $(this).val() + ",";
		}); 
	$.ajax({
		url : '/saveEditSubject',
		type : 'get',
		dataType : 'text',	
		data : {
			"id" : cur,
			"name":$("#edit-subjectName").val(),
			"rules":rules

		},
		success: function(data) {
			$('input[name="RulesE"]').each(function(){ 
			$(this).attr("checked", false);
			
			}); 
			if (data == "OK") {
				location.reload();
			} else {
				alert("修改失败");
			}
		}
	});	
})

$('#edit-fact-save').on('click', function() {
	$.ajax({
		url : '/editFactSave',
		type : 'get',
		dataType : 'text',	
		data : {
			"id" : cur,
			"name":$("#edit-factName").val(),
			"desc":$("#edit-factDesc").val()
		},
		success: function(data) {
			if (data == "OK") {
				location.reload();
			} else {
				alert("修改失败");
			}
		}
	});	
})


$('#edit-rule').on('click', function() {
	if (cur != null) {
//		
//		$("#edit-ruleName").val($("#"+cur).children('td').eq(0).text());
//		if($("#"+cur).children('td').eq(0).text()=="年龄") {
//			$("#s1").css('display','none'); 
//			$("#s2").css('display','none'); 
//			$("#edit-min").css('display','show'); 
//			$("#edit-max").css('display','show'); 
//			$("#edit-min").val($("#"+cur).children('td').eq(1).text());
//			$("#edit-max").val($("#"+cur).children('td').eq(2).text());
//		}
//		if($("#"+cur).children('td').eq(0).text()=="职称") {
//			$("#s1").css('display','show'); 
//			$("#s2").css('display','show'); 
//			$("#edit-min").css('display','none'); 
//			$("#edit-max").css('display','none'); 
//		}
//		
		$("#edit-ruleName").val($("#"+cur).children('td').eq(0).text());
		if($("#"+cur).children('td').eq(0).text()=="年龄") {
			$("#s1").css('display','none'); 
			$("#s2").css('display','none'); 
			$("#edit-min").css('display','show'); 
			$("#edit-max").css('display','show'); 
			$("#edit-min").val($("#"+cur).children('td').eq(1).text());
			$("#edit-max").val($("#"+cur).children('td').eq(2).text());
		}
		if($("#"+cur).children('td').eq(0).text()=="职称") {
			$("#s1").css('display','show'); 
			$("#s2").css('display','show'); 
			$("#edit-min").css('display','none'); 
			$("#edit-max").css('display','none'); 
		}
		


		$('#rule-modal').modal();
	} else {
		alert("请选择要编辑的规则");
	}
})

$('#edit-rule-save').on('click', function() {
	var min;
	var max;
	if($("#"+cur).children('td').eq(0).text()=="职称") {
	
		if ($("#s1").val() == "讲师") {
			min = 0;	
		}
		else if ($("#s1").val() == "副教授") {
			min = 1;
		} else {
			min = 2;
		}
		
		if ($("#s2").val() == "讲师") {
			max = 0;
		}
		else if ($("#s2").val() == "副教授") {
			max = 1;
		} else {
			max = 2;
		}
	} else {

		min = $("#edit-min").val();
		max = $("#edit-max").val();
	}

	if (min > max) {
		alert("请检查上下限范围");
	} else {
		$.ajax({
			url : '/editRuleSave',
			type : 'get',
			dataType : 'text',	
			data : {
				"id" : cur,
				"name":$("#edit-ruleName").val(),
				"min":min,
				"max":max,
			},
			success: function(data) {
				if (data == "OK") {
					location.reload();
				} else {
					alert("修改失败");
				}
			}
		});	
	}
})