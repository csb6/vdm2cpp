# VDM2CPP

A prototype translator of a subset of VDM-SL into C++, built as a plugin to [VDMJ](https://github.com/nickbattle/vdmj)

Intended as a small experiment and not a complete translator, but supports translating basic VDM type definitions and
function signatures into C++ equivalents.

## Types

|       VDM-SL             |              C++               |
| ------------------------ | ------------------------------ |
| `bool`                   | `bool`                         |
| `NamedType = OtherType`  | `using NamedType = OtherType;` |
| `TypeA \| TypeB`         | `std::variant<TypeA, TypeB>`   |
| `TypeA * TypeB`          | `std::tuple<TypeA, TypeB>`     |
| `Record :: a: A b: B`    | `struct Record { A a; B b; };` |
| `[A]`                    | `std::optional<A>`             |
| `set of char`            | `std::set<char>`               |
| `seq of char`            | `std::string`                  |
| `seq of A`               | `std::vector<A>`               |
| `map char to A`          | `std::map<char, A>`            |
| `A -> B`                 | `std::function<B(A)>`          |

Currently numeric types are not being translated properly; the idea is to translate `nat`, `int`, `rat`, etc. into
BigInt and BigFloat C++ types, and have annotations to mark VDM integer types with bounded sizes into corresponding
C++ primitive integer types.

## Functions

Currently only function signatures (not bodies) are translated. Curried functions are not translated,
and functions with parameters other than identifiers and ignore patterns are also not translated.

# License

All files in this repository are licensed under the GPLv3 license. See LICENSE for more details.
