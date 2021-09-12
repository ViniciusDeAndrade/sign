package br.com.sign.controller

import br.com.sign.form.ForgotPasswordForm
import br.com.sign.form.ResetPasswordDataForm
import br.com.sign.service.ClientRecoveryService
import org.springframework.context.MessageSource
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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
    @PostMapping(
        value = ["/reset"],
        consumes = [
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        ]
    )
    fun resetPassword(
        @RequestParam token: String,
        @RequestBody resetPasswordDataForm: ResetPasswordDataForm
    ): String {
        clientRecoveryService.updatePassword(resetPasswordDataForm);
        return "redirect:/password/succeed"
    }

    @GetMapping("/reset")
    fun getResetPasswordPage(
        @RequestParam token: String,
        model: Model
    ) : String {
        val client = clientRecoveryService.getClientByToken(token)
        model.addAttribute("token", token)
        return "password_change"
    }

    @GetMapping("/change")
    fun changePassword() = "forgot_password_form"

    @PostMapping("/change")
    fun changePassword(forgotPasswordForm: ForgotPasswordForm, model: Model): String {
        clientRecoveryService.forgottenPassword(forgotPasswordForm.email)
        return "redirect:/password/succeed"

    }

    @GetMapping("/succeed")
    fun succeed() = "forgot_password_succeed"

}