document.addEventListener("DOMContentLoaded", function() {

    const registerForm = document.getElementById('register-form');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const passwordError = document.getElementById('password-error');
    const apiError = document.getElementById('api-error');
    const submitButton = document.getElementById('submit-button');

    // Função de validação de senha (igual ao anterior)
    function validatePasswords() {
        if (password.value !== confirmPassword.value) {
            passwordError.textContent = 'As senhas não coincidem.';
            confirmPassword.style.borderColor = '#721c24'; // Vermelho
            return false;
        } else {
            passwordError.textContent = '';
            confirmPassword.style.borderColor = '#ddd';
            return true;
        }
    }

    password.addEventListener('keyup', validatePasswords);
    confirmPassword.addEventListener('keyup', validatePasswords);

    // Intercepta o envio do formulário
    registerForm.addEventListener('submit', async function(event) {
        event.preventDefault(); // Impede o envio tradicional
        apiError.style.display = 'none'; // Esconde erros antigos

        // 1. Validação local primeiro
        if (!validatePasswords()) {
            confirmPassword.focus();
            return;
        }

        // 2. Coleta os dados do formulário
        const formData = new FormData(registerForm);
        const data = {
            nome: formData.get('name'),
            cpf: formData.get('cpf'),
            email: formData.get('email'),
            matricula: formData.get('username'), // 'username' no form, 'matricula' na API
            password: formData.get('password')   // Veja a nota abaixo!
        };

        const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        // Desabilita o botão para evitar cliques duplos
        submitButton.disabled = true;
        submitButton.textContent = 'Registrando...';

        try {
            // 3. Envia os dados para a API REST
            const response = await fetch('/api/usuarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [header]: token
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                // 4. Sucesso! Redireciona para o login com a msg de sucesso
                window.location.href = '/login?registered=true';
            } else {
                // 5. Erro! Mostra a mensagem da API
                const errorData = await response.json();
                // Usa o formato de erro da sua API: { "error": "...", "message": "..." }
                apiError.textContent = errorData.message || 'Ocorreu um erro no registro.';
                apiError.style.display = 'block';
            }

        } catch (error) {
            // 6. Erro de rede/conexão
            apiError.textContent = 'Não foi possível conectar ao servidor. Tente novamente.';
            apiError.style.display = 'block';
        } finally {
            // Reabilita o botão
            submitButton.disabled = false;
            submitButton.textContent = 'Registrar';
        }
    });
});