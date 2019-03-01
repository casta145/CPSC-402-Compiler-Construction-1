#include "parser.h"
#include <string>

bool digit(char chr) {
    return (chr >= '0' && chr <= '9');
}

std::string cut_number(parser& p, bool& error) {
    error = false;
    //Check on that the first
    //symbol is a digit:
    if (!digit(p.peek())) {
        error = true;
        return "";
    }
    parser::const_iterator begin = p.current_it();
    //Check on that it is
    //correct number:
    if (p.get() == '0') {
        if (digit(p.peek())) {
            error = true;
            return "";
        }
    }
    p.pass(digit);
    //In the code below could not be
    //if (p.get() == '.')
    //because next char after *p.it
    //must be checked only if *p.it == '.'
    if (p.peek() == '.') {
        p.get();
        //Check on that it is
        //correct float pointing number:
        if (!digit(p.peek())) {
            error = true;
            return "";
        }
        else p.pass(digit);
    }
    parser::const_iterator end = p.current_it();
    return std::string(begin, end);
}
