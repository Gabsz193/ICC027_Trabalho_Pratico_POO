// Espera o documento carregar para executar o script
document.addEventListener("DOMContentLoaded", function() {

    const togglePassword = document.getElementById('togglePassword');
    const passwordInput = document.getElementById('password');

    if (togglePassword && passwordInput) {
        togglePassword.addEventListener('click', function() {
            // Verifica o tipo do input
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);

            // Altera o ícone (emoji)
            this.textContent = type === 'password' ? '👁️' : '🙈';
        });
    }

});