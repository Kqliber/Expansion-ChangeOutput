# Expansion-ChangeOutput
Change outputs of a placeholder on certain conditions

## Syntax

### `%changeoutput_<options>_<input>_<matcher>_<output-if-matched>_<output-if-not-matched>%`

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

#### `%changeoutput_equals_replace_replace_with this_instead of this%`
- Returns `with this` because `replace` matches `replace`

#### `%changeoutput_ignorecase_Replace_replace_case ignored_instead of this%`
- Returns `case ignored` because replace and Replace are the same, ignoring their cases

#### `%changeoutput_ignorecolor_{player_displayname}_Kqliber_Kaliber_Steve%`
- Let `{player_displayname}` = &4Kqliber
- Returns `Kaliber` because this option will ignore colours

#### `%changeoutput_contains_Steve_Ste_contains_does not contain%`
- Returns `contains` because Ste is in Steve

#### `%changeoutput_>_10_1_larger_smaller%`
- Returns `larger`

#### `%changeoutput_>=_10_10_larger_smaller%`
- Returns `larger`

#### `%changeoutput_<_1_10_smaller_larger%`
- Returns `larger`

### `%changeoutput_<=_10_10_smaller_larger%`
- Returns `smaller`
