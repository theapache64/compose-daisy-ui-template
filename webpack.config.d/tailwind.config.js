// must be in the jsMain/resource folder
const mainCssFile = 'styles.css';

// tailwind config (https://tailwindcss.com/docs/configuration)
const tailwind = {
    darkMode: 'media',
    plugins: [
        require('@tailwindcss/forms'), // optional
        require('daisyui') // optional
    ],
    daisyui: {
        themes: true, // false: only light + dark | true: all themes | array: specific themes like this ["light", "dark", "cupcake"]
        darkTheme: "dark", // name of one of the included themes for dark mode
        base: true, // applies background color and foreground color for root element by default
        styled: true, // include daisyUI colors and design decisions for all components
        utils: true, // adds responsive and modifier utility classes
        prefix: "", // prefix for daisyUI classnames (components, modifiers and responsive class names. Not colors)
        logs: true, // Shows info about daisyUI version and used config in the console when building your CSS
        themeRoot: ":root", // The element that receives theme color CSS variables
    },
    variants: {},
    theme: {
        extend: {},
    },
    content: {
        files: [
            '*.{js,html,css}',
            './kotlin/**/*.{js,html,css}'
        ],
        transform: {
            js: (content) => {
                return content.replaceAll(/(\\r)|(\\n)|(\\r\\n)/g,' ')
            }
        }
    },
};

// webpack tailwind css settings
((config) => {
    ((config) => {
        let entry = '../../../processedResources/js/main/' + mainCssFile;
        config.entry.main.push(entry);
        config.module.rules.push({
            test: /\.css$/,
            use: [
                {loader: 'style-loader'},
                {loader: 'css-loader'},
                {
                    loader: 'postcss-loader',
                    options: {
                        postcssOptions: {
                            plugins: [
                                require("tailwindcss")({config: tailwind}),
                                require("autoprefixer"),
                                require("cssnano")
                            ]
                        }
                    }
                }
            ]
        });
    })(config);
})(config);
