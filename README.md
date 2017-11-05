# Anagrams
You are given a vocabulary V of (lowercase) English words. It uses letters of English alphabet
[a-z], digits [0-9], and the apostrophe symbol [']. No other characters are used in the vocabulary V. Your goal is to
print out all valid anagrams of an input string. The input string will be a sequence of at most 12 characters.
Anagram: Two strings are anagrams of each other if by rearranging letters of one string you can obtain the other. For
example, “a bit” is an anagram of “bait”, and “super” is an anagram of “purse”. Note that we can add spaces at will,
i.e., we won’t count spaces when matching characters for checking anagrams.
In this assignment, you will load V from the text file and then be ready to compute anagrams. You will be provided an
input file also in the text format. In both vocabulary and input files there will be one string written per line. Your goal
will be compute all valid anagrams (i.e., each word within your anagram must be present in V) of all input strings. After
computing all valid anagrams of one string you must output a ‘-1’ to indicate that you are done computing anagrams
of this string. For the purpose of this assignment, you only have to compute anagrams with a maximum of 2 spaces in
them (i.e., three words at most). However, each permutation of these words will also be a valid anagram.

