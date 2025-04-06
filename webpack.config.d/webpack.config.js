const mainCssFile = 'styles.css';
const buildDirectory = '../../../'
const entryPath = buildDirectory + 'processedResources/js/main/' + mainCssFile;

((config) => {
  config.entry.main.push(entryPath);
  config.module.rules.push({
    test: /\.css$/,
    use: [
      { loader: 'style-loader' },
      { loader: 'css-loader' },
      {
        loader: 'postcss-loader',
        options: {
          postcssOptions: {
            plugins: [
              require('postcss-import')({
                path: [buildDirectory + 'js/node_modules']
              }),
              require('@tailwindcss/postcss')
            ]
          }
        }
      }
    ]
  });
})(config);