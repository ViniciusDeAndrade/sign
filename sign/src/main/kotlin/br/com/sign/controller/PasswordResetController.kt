package br.com.sign.controller

import br.com.sign.form.ForgotPasswordForm
import br.com.sign.form.ResetPasswordDataForm
import br.com.sign.service.ClientRecoveryService
import org.springframework.context.MessageSource
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
    fun resetPassword(@RequestBody forgotPasswordForm: ForgotPasswordForm, redirectAttributes: RedirectAttributes) {
        clientRecoveryService.forgottenPassword(forgotPasswordForm.email);
    }

    @GetMapping("/change")
    fun changePassword(
        @RequestParam(required = false) token: String,
        redirectAttributes: RedirectAttributes,
        model: Model
    ) = ""

    @PostMapping("/change")
    fun changePassword(resetPasswordData: ResetPasswordDataForm, model: Model) = ""
}