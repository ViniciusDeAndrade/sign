package br.com.sign.controller

import br.com.sign.form.ForgotPasswordForm
import br.com.sign.form.ResetPasswordDataForm
import br.com.sign.service.ClientRecoveryService
import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@RequestMapping("password")
@Controller
class PasswordResetController(
    private val messageSource: MessageSource,
    private val clientRecoveryService: ClientRecoveryService
) {

    /**
     * this method will deal with the link "forgot password" on the front end.
     * It will return a secure token
     */
    @PostMapping("/request")
    fun resetPassword(@RequestBody resetPasswordDataForm: ResetPasswordDataForm, redirectAttributes: RedirectAttributes) {
        clientRecoveryService.forgottenPassword(resetPasswordDataForm.email);
    }

    @GetMapping("/change")
    fun changePassword() = "forgot_password_form"

    @PostMapping("/change")
    fun changePassword(forgotPasswordForm: ForgotPasswordForm, model: Model): String {
        println(forgotPasswordForm)
        return "redirect:/password/succeed"

    }

    @GetMapping("/succeed")
    fun succeed() = "forgot_password_succeed"

}