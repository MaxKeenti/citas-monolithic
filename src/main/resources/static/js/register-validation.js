document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('password');
    const confirmInput = document.getElementById('confirmPassword');
    const registerButton = document.querySelector('button[type="submit"]');

    function validatePassword() {
        const password = passwordInput.value;
        const confirm = confirmInput.value;

        // Reset styles
        passwordInput.classList.remove('border-teal-500', 'border-rose-500');
        confirmInput.classList.remove('border-teal-500', 'border-rose-500');

        let isValid = true;

        // Length Check & Special Char Check
        const hasLength = password.length > 8;
        const hasSpecial = /[^a-zA-Z0-9]/.test(password);

        if (hasLength && hasSpecial) {
            passwordInput.classList.add('border-teal-500');
        } else {
            if (password.length > 0) { // Only show error if user has typed something
                passwordInput.classList.add('border-rose-500');
            }
            isValid = false;
        }

        // Match Check
        if (password === confirm && password !== '') {
            confirmInput.classList.add('border-teal-500');
        } else {
            if (confirm.length > 0) {
                 confirmInput.classList.add('border-rose-500');
            }
            isValid = false;
        }
        
        // Optional: Disable submit button until valid? 
        // For now, we rely on backend validaiton too, but visual feedback is key.
    }

    if (passwordInput && confirmInput) {
        passwordInput.addEventListener('input', validatePassword);
        confirmInput.addEventListener('input', validatePassword);
    }
});
