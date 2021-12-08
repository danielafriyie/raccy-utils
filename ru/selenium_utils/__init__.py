import random as rd
import time

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.common.exceptions import (
    TimeoutException, WebDriverException, ElementClickInterceptedException,
    StaleElementReferenceException
)


def window_scroll_to(driver, loc):
    driver.execute_script(f"window.scrollTo(0, {loc});")


def scroll_into_view(driver, element, offset=200):
    window_scroll_to(driver, element.location['y'] - offset)


def driver_wait(driver, xpath, secs=10, condition=EC.element_to_be_clickable, action=None, *args, **kwargs):
    wait = WebDriverWait(driver=driver, timeout=secs)
    element = wait.until(condition((By.XPATH, xpath)))
    if action:
        if hasattr(element, action):
            action_func = getattr(element, action)
            action_func(*args, **kwargs)
    return element


def driver_or_js_click(driver, xpath, secs=5, condition=EC.element_to_be_clickable):
    try:
        elm = driver_wait(driver, xpath, secs=secs, condition=condition)
        ActionChains(driver).move_to_element(elm).click().perform()
    except (TimeoutException, ElementClickInterceptedException, StaleElementReferenceException):
        elm = driver.find_element_by_xpath(xpath)
        try:
            ActionChains(driver).move_to_element(elm).click().perform()
        except WebDriverException:
            driver.execute_script("arguments[0].click()", elm)


def manual_entry(driver, xpath, text, secs=10, condition=EC.element_to_be_clickable, *args, **kwargs):
    driver_wait(
        driver,
        xpath,
        secs=secs,
        condition=condition
    )
    elm = driver.find_element_by_xpath(xpath)
    ActionChains(driver).move_to_element(elm).perform()
    elm.clear()
    text = f"{text}"
    for letter in text:
        elm.send_keys(letter)
        time.sleep(0.05)
    time.sleep(1)
    elm.send_keys(*args, **kwargs)


def random_delay(a=1, b=3):
    delay = rd.randint(a, b)
    precision = delay / (a + b)
    sleep_time = delay + precision
    time.sleep(sleep_time)
