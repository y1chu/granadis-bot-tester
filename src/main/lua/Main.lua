print(type("Rory"))
t = 10
print(type(5.8*t))
print(#"rory");
print("kenji".." rory")

a = 10

repeat
    print("value of a:", a)
    a = a + 1
until( a > 15 )
print("it is now > 15")
if (a > 10)
then
    print("I love rory")
end

function addTog(num1, num2)
    result = num1 + num2
    return result
end

function max(num1, num2)

    if (num1 > num2) then
        result = num1;
    else
        result = num2;
    end

    return result;
end

print(addTog(12,1))
print(string.upper("rory"))
c = string.gsub("Today i went to see rory im happy"," ","-")
print(c)
print(string.rep("rory", 10))