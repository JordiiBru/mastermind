package domain;

import domain.exceptions.LengthCodeTooSmallException;

import java.util.List;

/**
 * @author marcel
 */
public interface Computer {

    List<List<Integer>> solve(List<Integer> solution) throws Exception;
}
