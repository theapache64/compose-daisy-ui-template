// tailwind.config.js
module.exports = {
    content: ["**/*.{html,js}"], // This is important
    theme: {
        extend: {},
    },
    lugins: [require("daisyui")],
    daisyui: {
        themes: ["retro"], // Add the "retro" theme
    },
}