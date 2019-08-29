"use strict";

//Plaeholder handler
$(function () {

    if (!Modernizr.input.placeholder) {             //placeholder for old brousers and IE

        $('[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').parents('form').submit(function () {
            $(this).find('[placeholder]').each(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }

    $('#contact-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $name = self.find('[name=user-name]');
        var $email = self.find('[type=email]');
        var $message = self.find('[name=user-message]');


        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult('Error! Wrong email!', $email);
            error++;
        }

        if ($name.val().length > 1 && $name.val() != $name.attr('placeholder')) {
            $name.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write your name!', $name);
            error++;
        }

        if ($message.val().length > 2 && $message.val() != $message.attr('placeholder')) {
            $message.removeClass('invalid_field');
        } else {
            createErrTult('Error! Write message!', $message);
            error++;
        }


        if (error != 0) return;
        self.find('[type=submit]').attr('disabled', 'disabled');

        self.children().fadeOut(300, function () {
            $(this).remove()
        });
        $('<p class="success"><span class="success-huge">Thank you!</span> <br> your message successfully sent</p>').appendTo(self)
            .hide().delay(300).fadeIn();


        var formInput = self.serialize();
        $.post(self.attr('action'), formInput, function (data) {
        }); // end post
    }); // end submit

    $('#login-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $email = self.find('[type=email]');
        var $pass = self.find('[type=password]');


        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult("Error! Wrong email!", $email);
            error++;
        }

        if ($pass.val().length > 1 && $pass.val() != $pass.attr('placeholder')) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('Error! Wrong password!', $pass);
            error++;
        }
        var formInput = self.serialize();
        $.post('/login', formInput, function (data) {
            if (data.msg == "成功") {
                alert(data.msg);
                self.children().fadeOut(300, function () {
                    $(this).remove()
                });
                $('<p class="login__title">sign in <br><span class="login-edition">welcome to A.Movie</span></p><p class="success">You have successfully<br> signed in!</p>').appendTo(self)
                    .hide().delay(300).fadeIn();
                if (error != 0) return;
                self.find('[type=submit]').attr('disabled', 'disabled');
                setTimeout(function () {
                    window.location.reload();
                }, 1000)
            } else {
                alert(data.msg)
            }

        });
        // var formInput = self.serialize();
        // $.post(self.attr('action'),formInput, function(data){}); // end post
    }); // end submit


    //注册
    $('#register-form').submit(function (e) {

        e.preventDefault();
        var error = 0;
        var self = $(this);

        var $name = self.find('[type=text]');
        var $pass = self.find('[type=password]');
        var $email = self.find('[type=email]');
        var $tel = self.find('[type=tel]');
        var $gender = self.find('[type=gender]');

        var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (!emailRegex.test($email.val())) {
            createErrTult("Error! Wrong email!", $email);
            error++;
        }

        if ($pass.val().length > 1 && $pass.val() != $pass.attr('placeholder')) {
            $pass.removeClass('invalid_field');
        } else {
            createErrTult('Error! Wrong password!', $pass);
            error++;
        }

        var  telRegex = /^1([38]\d|5[0-35-9]|7[3678])\d{8}$/;

        if (!telRegex.test($tel.val())) {
            createErrTult("Error! Wrong telephone!",$tel);
            error++;
        }

        var data = {
            nickname:$name.val(),
            password:$pass.val(),
            phone:$tel.val(),
            email:$email.val(),
            gender:$gender.val(),
        };
        $.ajax({
            url:'/register',
            method:'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType:'json',
            success:function (data) {
                alert(data.message);
                self.children().fadeOut(300,function () {
                    $(this).remove()
                });
                window.location.href = "/login.html";
                if (error != 0) return;
                self.find('[type = submit]').attr('disabled','disabled');
            },
            error:function (data) {
                alert(data.message);
            }
        }
        /*var formInput = self.serialize();
        $.post('register', formInput, function (data) {
            if (data.msg == "成功") {
                alert(data.msg);
                setTimeout(function () {
                    window.location.href = "/login.html";
                }, 1000)
            } else {
                alert(data.msg)
            }
        }*/);
    }); // end submit


    function createErrTult(text, $elem) {
        $elem.focus();
        $('<p />', {
            'class': 'inv-em alert alert-danger',
            'html': '<span class="icon-warning"></span>' + text + ' <a class="close" data-dismiss="alert" href="#" aria-hidden="true"></a>',
        })
            .appendTo($elem.addClass('invalid_field').parent())
            .insertAfter($elem)
            .delay(4000).animate({'opacity': 0}, 300, function () {
            $(this).slideUp(400, function () {
                $(this).remove()
            })
        });
    }
});
