public static int[] searchRange(int[] nums, int target) {
		int end = nums.length;
		int start = 0;
		int startPos = -1;
		int endPos = -1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (target == nums[mid]) {
				startPos = mid;
				endPos = mid;

				int tmp = findVal(start, mid, nums, target, mid, "lower");
				if (tmp != -1 && tmp < startPos)
					startPos = tmp;
				tmp = findVal(mid, end, nums, target, mid, "higher");
				if (tmp != -1 && endPos < tmp)
					endPos = tmp;
				break;

			} else if (target < nums[mid]) {

				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		int[] targetRange = new int[2];
		targetRange[0] = startPos;
		targetRange[1] = endPos;
		return targetRange;
	}

	public static int findVal(int start, int end, int[] nums, int target, int pos, String dir) {

		if (start > end) {
			return pos;
		}
		int mid = (end + start) / 2;

		if (mid < nums.length) {
			if (target == nums[mid]) {
				pos = mid;
				if (dir.equals("lower")) {
					int tmp = findVal(start, mid - 1, nums, target, pos, dir);
					if (tmp < pos)
						pos = tmp;
				} else {
					int tmp = findVal(mid + 1, end, nums, target, pos, dir);
					if (tmp> pos)
						pos = tmp;
				}
				return pos;
			} else if (nums[mid] < target && dir.equals("lower")) {
				int tmp = findVal(mid + 1, end, nums, target, pos, dir);
				if (tmp < pos)
					pos = tmp ;

			} else if (nums[mid] > target && dir.equals("higher")) {

				int tmp = findVal(start, mid - 1, nums, target, pos, dir);
				if (tmp > pos)
					pos = tmp;
			}
		}
		return pos;

	}

