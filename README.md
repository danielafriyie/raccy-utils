# RACCY-UTILS

### Overview
Raccy-Utils is a collection of utility functions and classes for various python libraries.

### Requirements
 - Python 3.7+
 - Works on Linux, Windows, and Mac

### Example, working with  config files
Currently this library supports only json and text config files, and it does not support comments at the moment.

```python
from ru import TextConfig

config = TextConfig()
print(config['foo'])
```
```shell script
foo
```

```python
from ru import JsonConfig

config = JsonConfig()
print(config['foo'])
```
```shell script
foo
```

### Example, working with selenium

```python
from ru import manual_entry
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome()
driver.get('https://google.com')
manual_entry(driver, '//input[@title="Search"]', 'raccy-utils', Keys.ENTER)
```

### Installation

```shell script
pip install raccy-utils
```