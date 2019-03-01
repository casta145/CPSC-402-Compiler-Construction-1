#ifndef PARSER_H_
#define PARSER_H_

#include <string>

class parser {
public:
    typedef std::string::const_iterator const_iterator;
private:
    static const_iterator start_position(bool b, const std::string& str);
    static const_iterator begin_it(const parser& p);
    static const_iterator end_it(const parser& p);
    static const_iterator current_it(const parser& p);
    const_iterator begin;
    const_iterator end;
    const_iterator it;
public:

    parser(): begin(NULL), end(NULL), it(NULL) {};

    parser
    (
        const std::string& str_to_parse,
        bool start_from_the_end
    ):
        begin(str_to_parse.begin()),
        end  (str_to_parse.end()),
        it   (start_position(start_from_the_end, str_to_parse)) {};

    //Give another string to parser:
    void str(const std::string&);

    void set_to_begin();
    void set_to_end();

    parser& operator = (const parser&);

    bool eof_str() const;

    char get();         //move forward
    char rget();        //move backward
    char peek() const;  //watch current symbol

    //pass an all symbols beginning from current:
    void pass(char);     //moving forward
    void rpass(char);    //moving backward
    //pass an all symbols beginning from
    //current which satisfy to condition:
    void pass(bool (*)(char));  //moving forward
    void rpass(bool (*)(char)); //moving backward

    //return iterator:
    const_iterator current_it() const;

};

//This function is used in constructor:
//it helps to set iterator of parser
//to beginning or to the end of string.
inline
parser::const_iterator parser::start_position(bool b, const std::string& str) {

    if (b)
        return str.end();

    return str.begin();
}

//This functions are used in operator=.
//I decided to do not writing analogous
//const-functions for better encapsulation.
inline
parser::const_iterator parser::begin_it(const parser& p) {return p.begin;}
inline
parser::const_iterator parser::end_it(const parser& p) {return p.end;}
inline
parser::const_iterator parser::current_it(const parser& p) {return p.it;}

inline
void parser::str(const std::string& str_to_parse) {
    begin = str_to_parse.begin();
    end   = str_to_parse.end();
    it    = str_to_parse.begin();
}

inline
void parser::set_to_begin() {it = begin;}

inline
void parser::set_to_end() {it = end;}

inline
parser& parser::operator = (const parser& p) {
    begin = begin_it(p);
    end   = end_it(p);
    it    = current_it(p);
    return *this;
}

inline
bool parser::eof_str() const {return it >= end;}

inline
char parser::get() {return *(it++);}

inline
char parser::rget() {return *(it--);}

inline
char parser::peek() const {return *it;}

inline
void parser::pass(char chr) {
    while (*it == chr) ++it;
}

inline
void parser::rpass(char chr) {
    while (*it == chr) --it;
}

inline
void parser::pass(bool (*cond)(char)) {
    while (cond(*it)) ++it;
}

inline
void parser::rpass(bool (*cond)(char)) {
    while (cond(*it)) --it;
}

inline
parser::const_iterator parser::current_it() const {return it;}

#endif /* PARSER_H_ */
