# Expansion-ChangeOutput
Change outputs of a placeholder on certain conditions

## Syntax

#### `%changeoutput_<options>_input:<input>_matcher:<matcher>_ifmatch:<output-if-matched>_else:<output-if-not-matched>%`

* \<options>
  * equals - match the input exactly
  * ignorecase - match the input while ignoring cases
  * ignorecolor - match the input while ignoring colour codes
  * contains - check if the match contains input
  * \>= - check if the input is larger than or equal to the matcher
  * \> - check if the input is larger than the matcher
  * <= - check if the input is less than or equal to the matcher
  * < - check if the input is less than the matcher
* \<input> - this is your text that you wish to replace
* \<match> - this is the text we will be looking for to meet the conditions
* \<output-if-matched> - if the input meets the condition, this text will be displayed
* \<output-if-not-matched> - if the input does not meet the condition, this text will be displayed instead

*All arguments can be replaced with other placeholders, wrapped in* `{}`

## Examples

#### `%changeoutput_equals_input:replace_matcher:replace_ifmatch:with this_else:instead of this%`
- Returns `with this` because `replace` matches `replace`

#### `%changeoutput_ignorecase_input:Replace_matcher:replace_ifmatch:case ignored_else:instead of this%`
- Returns `case ignored` because replace and Replace are the same, ignoring their cases

#### `%changeoutput_ignorecolor_input:{player_displayname}_matcher:Kqliber_ifmatch:Kaliber_else:Steve%`
- Let `{player_displayname}` = &4Kqliber
- Returns `Kaliber` because this option will ignore colours

#### `%changeoutput_contains_input:Steve_matcher:Ste_ifmatch:contains_else:does not contain%`
- Returns `contains` because Ste is in Steve

#### `%changeoutput_>_input:10_matcher:1_ifmatch:larger_else:smaller%`
- Returns `larger`

#### `%changeoutput_>=_input:10matcher:_10_ifmatch:larger_else:smaller%`
- Returns `larger`

#### `%changeoutput_<_input:1_matcher:10_ifmatch:smaller_else:larger%`
- Returns `larger`

### `%changeoutput_<=_input:10_matcher:10_ifmatch:smaller_else:larger%`
- Returns `smaller`
