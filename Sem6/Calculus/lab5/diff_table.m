function f = diff_table(x, dy)
    
    %x - node values
    %dy derivative table, falues for f^(i) are on line i
    
    n = 2 * length(x);
    f = ones(n);
    double_nodes = repelem(x, 2);
    f(:, 1) = repelem(dy(1,:), 2);
    f(1:2:end-1, 2) = dy(2,:);
    f(2:2:end-2, 2) = diff(dy(1,:))'./ diff(x)';
    for j = 3:n
        for i = 1:n-j+1
            f(i, j) = (f(i+1, j-1) - f(i, j-1)) ./ (double_nodes(i+j-1) - double_nodes(i));
        end
    end
end