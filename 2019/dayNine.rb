require_relative 'computer'
$pr = File.read('dayNine.txt').split(',').map(&:to_i)

print "Day nine part one: "
runProgram(nil, 1, $pr.dup, 0, false)
print "Day nine part two: "
runProgram(nil, 2, $pr.dup, 0, false)