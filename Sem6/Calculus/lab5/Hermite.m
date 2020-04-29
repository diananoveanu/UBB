function [X, Y] = Hermite(nodes, dy, x)
  
    f = diff_table(nodes, dy);
    coefs = f(1,:);
    double_nodes = repelem(nodes, 2);
    X = coefs(1);
    Y = 0;
    p = 1;
    der_p = 0;
    for k = 2:length(coefs)
        der_p = der_p .* (x - double_nodes(k - 1)) + p;
        p = p .* (x - double_nodes(k - 1));
        X = X + coefs(k) .* p;
        Y = Y + coefs(k) .* der_p;
    end
end