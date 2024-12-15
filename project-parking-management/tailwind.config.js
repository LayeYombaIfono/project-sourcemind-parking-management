/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./public/**/*.{html,js}"
  ],
  theme: {
    extend: {
      colors: {
        primary: '#3490DC',
        secondary: '#F1C40F',
        success: '#2ECC71',
        warning: '#E67E22',
        danger: '#E74C3C',
        light: '#ECF0F1',
        dark: '#34495E',
      },
      fontFamily: {
        'sans': ['Poppins', 'sans-serif'],
        'heading': ['Poppins', 'sans-serif'],

      },
    },
  },
  plugins: [

  ],
}

