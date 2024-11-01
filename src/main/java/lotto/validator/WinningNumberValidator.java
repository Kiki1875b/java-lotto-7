package lotto.validator;

import lotto.constant.ErrorConstants;
import lotto.util.Separator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumberValidator implements Validator{

    private static final String REGEX = "^([1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-5])";
    private static final String REGEX_WITH_SEPARATOR = "(,([1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-5]))";
    private static final String NUMBER_OF_REPEATS = "{5}$";
    private static final int NUMBER_OF_ELEMENTS = 6;
    private static final String COMBINED_REGEX = REGEX + REGEX_WITH_SEPARATOR + NUMBER_OF_REPEATS;

    @Override
    public void validate(String input){
        checkElementIsEmpty(input);
        checkElementIsNull(input);
        checkElementMatchesRegex(input);
        checkDuplicateNumber(input);
    }

    private void checkElementIsEmpty(String input){
        if(input.isEmpty()){
            throw new IllegalArgumentException(ErrorConstants.INVALID_WINNING_NUMBER_FORMAT.getMessage());
        }
    }

    private void checkElementIsNull(String input){
        if(input == null){
            throw new IllegalArgumentException(ErrorConstants.INVALID_WINNING_NUMBER_FORMAT.getMessage());
        }
    }

    private void checkElementMatchesRegex(String input){
        if(!input.matches(COMBINED_REGEX)){
            throw new IllegalArgumentException(ErrorConstants.INVALID_WINNING_NUMBER_FORMAT.getMessage());
        }
    }

    private void checkDuplicateNumber(String input){
        List<String> parsedInput = Separator.separate(input);
        HashSet<String> uniqueElements = convertToSet(parsedInput);
        if(uniqueElements.size() < NUMBER_OF_ELEMENTS){
            throw new IllegalArgumentException(ErrorConstants.DUPLICATE_NOT_ALLOWED.getMessage());
        }
    }

    private HashSet<String> convertToSet(List<String> parsedInput){
        return new HashSet<>(parsedInput);
    }
}
