# input = File.read("dayTen.txt").split("") - ["\n"]
input = 
"......#.#.
#..#.#....
..#######.
.#.#.###..
.#..#.....
..#....#.#
#..#....#.
.##.#..###
##...#..#.
.#....####".split("") - ["\n"]
width = Math.sqrt(input.length).to_i

Coord = Struct.new(:x,:y)

best = 0
for i in 0..input.length-1
    if input[i] == "."
        next
    end
    visible = []
    x1 = i%width
    y1 = i/width
    for j in 0..input.length-1
        if i == j
            next
        end
        x2 = j%width
        y2 = j/width
        deltaX = x1-x2
        deltaY = y1-y2
        g = deltaX.gcd(deltaY)
        if g == 1
            if input[j] == "#"
                c = Coord.new(x2,y2)
                unless visible.include? c
                    visible << c
                end
            else
                looking = true
                k = y2*width + x2
                while x2 > 0 && x2 < width-1 && y2 > 0 && y2 < width-1 && looking
                    x2 += deltaX
                    y2 += deltaY
                    k = y2*width + x2
                    if input[k] == "#"
                        looking = false
                        c = Coord.new(x2,y2)
                        unless visible.include? c
                            visible << c
                        end
                    end
                end
            end
        end
    end
    if x1 == 5 && y1 == 8
        for j in 0..input.length-1
            if j%width == 0
                puts
            end
            if i == j
                print "x"
            elsif visible.include? Coord.new(j%width, j/width)
                print "#"
            else 
                print "."
            end
        end
    puts
    end
    best = [visible.length,best].max
end

puts best