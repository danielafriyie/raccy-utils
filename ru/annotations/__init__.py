import os
import typing

Cast = typing.Callable[[typing.Any], typing.Any]
Path = typing.Union[str, os.PathLike[str]]
Config = typing.Dict[str, str]
