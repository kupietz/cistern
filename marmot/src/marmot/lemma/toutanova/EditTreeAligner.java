package marmot.lemma.toutanova;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import marmot.util.edit.EditTree;
import marmot.util.edit.EditTreeBuilder;
import marmot.util.edit.Match;
import marmot.util.edit.MatchNode;
import marmot.util.edit.ReplaceNode;

public class EditTreeAligner implements Aligner {

	private EditTreeBuilder builder_;

	public EditTreeAligner(EditTreeBuilder builder) {
		builder_ = builder;
	}

	@Override
	public List<Integer> align(String input, String output) {
		EditTree tree = builder_.build(input, output);
		List<Integer> list = new LinkedList<>();
		readAlignment(tree, list, 0, input.length(), 0, output.length());

		List<Integer> merged_list = Aligner.StaticMethods
				.mergeEmptyInputSegments(list);

		if (!merged_list.equals(list)) {
			Logger.getLogger(getClass().getName()).info(
					String.format("Merging: %s %s %s -> %s", input, output,
							list, merged_list));
		}

		return merged_list;
	}

	private void readAlignment(EditTree tree, List<Integer> list,
			int input_start, int input_end, int output_start, int output_end) {
		if (tree.getClass() == ReplaceNode.class) {
			list.add(input_end - input_start);
			list.add(output_end - output_start);
			return;
		}

		MatchNode match_node = (MatchNode) tree;

		int input_length = 0;
		int output_length = 0;

		EditTree left = match_node.getLeft();
		if (left != null) {
			input_length += left.getInputLength();
			output_length += left.getOutputLength();
			readAlignment(left, list, input_start, input_start + input_length,
					output_start, output_start + output_length);
		}

		Match match = match_node.getMatch();
		input_length += match.getLength();
		output_length += match.getLength();

		for (int i = 0; i < match.getLength(); i++) {
			list.add(1);
			list.add(1);
		}

		EditTree right = match_node.getRight();
		if (right != null) {
			readAlignment(right, list, input_start + input_length, input_end,
					output_start + output_length, output_end);
		}
	}

}