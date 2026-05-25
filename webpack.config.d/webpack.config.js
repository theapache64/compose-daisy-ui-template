const path = require('path');
const mainCssFile = 'styles.css';
const entryPath = path.resolve(__dirname, 'kotlin', mainCssFile);
const jsBuildDirectory = path.resolve(__dirname, '../..');
const nodeModulesDirectory = path.resolve(jsBuildDirectory, 'node_modules');

const resolveTailwindPluginsFromKotlinJsNodeModules = {
  postcssPlugin: 'resolve-tailwind-plugins-from-kotlin-js-node-modules',
  Once(root) {
    root.walkAtRules('plugin', (atRule) => {
      const pluginName = atRule.params.trim().slice(1, -1);
      if (!pluginName || pluginName.startsWith('.') || path.isAbsolute(pluginName)) return;
      const pluginPackageJson = require.resolve(`${pluginName}/package.json`, { paths: [nodeModulesDirectory] });
      atRule.params = JSON.stringify(path.dirname(pluginPackageJson));
    });
  }
};

((config) => {
  if (!config.entry.main.includes(entryPath)) {
    config.entry.main.push(entryPath);
  }
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
              require('postcss-import')({ path: [nodeModulesDirectory] }),
              resolveTailwindPluginsFromKotlinJsNodeModules,
              require('@tailwindcss/postcss')({ base: jsBuildDirectory })
            ]
          }
        }
      }
    ]
  });
})(config);